package com.emrekentli.adoptme.domain.authentication.user.impl;

import com.emrekentli.adoptme.domain.authentication.user.api.UserRetrievalService;
import com.emrekentli.adoptme.domain.authentication.user.api.UserRoleDto;
import com.emrekentli.adoptme.domain.email.api.EmailDto;
import com.emrekentli.adoptme.domain.email.api.EmailService;
import com.emrekentli.adoptme.domain.authentication.role.api.RoleDto;
import com.emrekentli.adoptme.domain.authentication.role.impl.Role;
import com.emrekentli.adoptme.domain.authentication.role.impl.RoleServiceImpl;
import com.emrekentli.adoptme.domain.authentication.user.api.UserDto;
import com.emrekentli.adoptme.domain.authentication.user.api.UserService;
import com.emrekentli.adoptme.library.enums.MessageCodes;
import com.emrekentli.adoptme.library.exception.CoreException;
import com.emrekentli.adoptme.library.util.PageUtil;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final RoleServiceImpl roleService;
    private final UserRetrievalService userRetrievalService;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;
    @Override
    @Transactional
    public UserDto createUser(UserDto dto) {
        checkUserExists(dto.getEmail());
        String randomPassword =  randomPassword();
        User user = toEntity(new User(), randomPassword, dto);
        emailService.sendEmail(EmailDto.builder()
                .to(user.getEmail())
                .subject("Welcome to Pokedex")
                .text("Welcome to Pokedex!\n Your password is " + randomPassword)
                .build());
        return toDto(repository.save(user));
    }

    @Override
    @Transactional
    public UserDto updateUser(String id, UserDto dto) {
        return repository.findById(id)
                .map(user -> toEntity(user, dto))
                .map(repository::save)
                .map(this::toDto)
                .orElseThrow(() -> new CoreException(MessageCodes.ENTITY_NOT_FOUND,User.class.getSimpleName(),id));
    }

    @Override
    @Transactional
    public void deleteUser(String id) {
        repository.delete(repository.findById(id).orElseThrow(() -> new CoreException(MessageCodes.ENTITY_NOT_FOUND,User.class.getSimpleName(),id)));
    }

    @Override
    public Page<UserDto> getAllUserPageable(Pageable pageable) {
        return PageUtil.pageToDto(repository.findAll(pageable), this::toDto);
    }

    @Override
    public Page<UserDto> filterUser(UserDto dto, Pageable pageable) {
        return repository.filterUser(dto.getUserName(), dto.getFullName(), dto.getEmail(), dto.getPhoneNumber(), pageable)
                .map(this::toDto);
    }

    @Override
    public UserDto getUserDtoById(String id) {
        return toDto(repository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found")));
    }

    @Override
    public UserDto getByUserName(String userName) {
        return repository.findByUserName(userName)
                .map(this::toDto)
                .orElseThrow(() -> new CoreException(MessageCodes.ENTITY_NOT_FOUND,User.class.getSimpleName(),userName));
    }

    @Override
    public UserDto updateMyUser(UserDto dto) {
      return repository.findById(userRetrievalService.getCurrentUserId())
                .map(user -> toEntity(user, dto))
                .map(repository::save)
                .map(this::toDto)
              .orElseThrow(() -> new CoreException(MessageCodes.ENTITY_NOT_FOUND,User.class.getSimpleName(),userRetrievalService.getCurrentUserId()));
    }
    @Override
    public UserDto addRoleToUser(UserRoleDto dto) {
        var user = repository.findById(dto.getUserId()).orElseThrow(() -> new CoreException(MessageCodes.ENTITY_NOT_FOUND,User.class.getSimpleName(),dto.getUserId()));
        var role = roleService.findRoleById(dto.getRoleId());
        checkUserHasRole(user, role);
        user.getRoles().add(role);
        return toDto(repository.save(user));
    }

    @Override
    public UserDto removeRoleToUser(UserRoleDto dto) {
        var user = repository.findById(dto.getUserId()).orElseThrow(() -> new CoreException(MessageCodes.ENTITY_NOT_FOUND,User.class.getSimpleName(),dto.getUserId()));
        var role = roleService.findRoleById(dto.getRoleId());
        if(role.getName().equals("ROLE_ADMIN")){
            throw new CoreException(MessageCodes.FAIL);
        }
        user.getRoles().remove(role);
        return toDto(repository.save(user));
    }

    private void checkUserHasRole(User user, Role role) {
        if (user.getRoles().contains(role)) {
            throw new IllegalArgumentException("User has this role");
        }
    }

    public User getUserByUserName(String username) {
        return repository.findByUserName(username).orElseThrow(() -> new CoreException(MessageCodes.ENTITY_NOT_FOUND,User.class.getSimpleName(),username));
    }

    public void saveUser(User user) {
        repository.save(user);
    }

    public User toEntity(User user, UserDto dto) {
        user.setUserName(dto.getUserName());
        user.setPassword(passwordEncoder.encode(randomPassword()));
        user.setActivity(true);
        user.setFullName(dto.getFullName());
        user.setImage(dto.getImage());
        user.setEmail(dto.getEmail());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setRoles(dto.getRoles() != null ? roleService.getRolesByRoleNames(dto.getRoles().stream().map(RoleDto::getName).collect(Collectors.toSet())) : null);
        return user;
    }
    public User toEntity(User user, String password, UserDto dto) {
        user.setUserName(dto.getUserName());
        user.setPassword(passwordEncoder.encode(password));
        user.setActivity(true);
        user.setFullName(dto.getFullName());
        user.setEmail(dto.getEmail());
        user.setImage(dto.getImage());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setRoles(dto.getRoles() != null ? roleService.getRolesByRoleNames(dto.getRoles().stream().map(RoleDto::getName).collect(Collectors.toSet())) : roleService.getByRoleEntityByName("ROLE_USER"));
        return user;
    }

    private String randomPassword() {
        int randomPIN = (int) (Math.random() * 900000) + 100000;
        return String.valueOf(randomPIN);
    }

    public UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .created(user.getCreated())
                .modified(user.getModified())
                .userName(user.getUserName())
                .image(user.getImage())
                .phoneNumber(user.getPhoneNumber())
                .email(user.getEmail())
                .activity(user.getActivity())
                .roles(user.getRoles() != null ? user.getRoles().stream().map(Role::toDto).collect(Collectors.toSet()) : null)
                .fullName(user.getFullName())
                .build();
    }


    public void checkUserExists(String userName) {
        repository.findByEmail(userName)
                .ifPresent(u -> {
                    throw new CoreException(MessageCodes.ENTITY_ALREADY_EXISTS,User.class.getSimpleName(),userName);
                });
    }
}
