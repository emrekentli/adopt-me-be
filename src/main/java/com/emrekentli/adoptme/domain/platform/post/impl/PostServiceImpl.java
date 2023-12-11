package com.emrekentli.adoptme.domain.platform.post.impl;

import com.emrekentli.adoptme.domain.authentication.user.api.UserService;
import com.emrekentli.adoptme.domain.platform.animaltype.api.AnimalTypeService;
import com.emrekentli.adoptme.domain.platform.breed.api.BreedService;
import com.emrekentli.adoptme.domain.platform.city.api.CityService;
import com.emrekentli.adoptme.domain.platform.district.api.DistrictService;
import com.emrekentli.adoptme.domain.platform.post.api.PostDto;
import com.emrekentli.adoptme.domain.platform.post.api.PostService;
import com.emrekentli.adoptme.library.enums.MessageCodes;
import com.emrekentli.adoptme.library.exception.CoreException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository repository;
    private final UserService userService;
    private final BreedService breedService;
    private final CityService cityService;
    private final DistrictService districtService;
    private final AnimalTypeService animalTypeService;

    @Override
    public PostDto create(PostDto dto) {
        return toDto(repository.save(PostMapper.toEntity(new Post(),dto)));
    }

    @Override
    public List<PostDto> getAll() {
        return repository.findAll().stream().map(this::toDto).toList();
    }

    @Override
    public PostDto update(String id, PostDto dto) {
        return toDto(repository.save(PostMapper
                .toEntity(repository.findById(id)
                        .orElseThrow( () -> new CoreException(MessageCodes.ENTITY_NOT_FOUND, Post.class.getSimpleName())),dto)));
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }

    @Override
    public List<PostDto> filter(PostDto post) {
        return repository.filter(
                post.getOwner().getId(),
                post.getGender(),
                post.getTitle(),
                post.getDescription(),
                post.getAnimalType().getId(),
                post.getBreed().getId(),
                post.getCity().getId(),
                post.getDistrict().getId(),
                post.getVerified(),
                post.getStatus()).stream().map(this::toDto).toList();
    }

    public PostDto toDto(Post post){
        return PostDto.builder()
                .id(post.getId())
                .created(post.getCreated())
                .modified(post.getModified())
                .owner(userService.getById(post.getOwnerId()))
                .title(post.getTitle())
                .description(post.getDescription())
                .gender(post.getGender())
                .animalType(animalTypeService.getById(post.getAnimalTypeId()))
                .breed(breedService.getById(post.getAnimalBreedId()))
                .age(post.getAge())
                .verified(post.getVerified())
                .status(post.getStatus())
                .city(cityService.getById(post.getCityId()))
                .district(districtService.getById(post.getDistrictId()))
                .mainImage(post.getMainImage())
                .images(post.getImages())
                .build();
    }
}
