package com.emrekentli.adoptme.domain.platform.post.impl;

import com.emrekentli.adoptme.domain.authentication.user.api.UserRetrievalService;
import com.emrekentli.adoptme.domain.authentication.user.api.UserService;
import com.emrekentli.adoptme.domain.platform.animaltype.api.AnimalTypeService;
import com.emrekentli.adoptme.domain.platform.breed.api.BreedService;
import com.emrekentli.adoptme.domain.platform.city.api.CityService;
import com.emrekentli.adoptme.domain.platform.district.api.DistrictService;
import com.emrekentli.adoptme.domain.platform.media.api.MediaDto;
import com.emrekentli.adoptme.domain.platform.media.api.MediaService;
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
    private final MediaService mediaService;
    private final UserRetrievalService userRetrievalService;

    @Override
    public PostDto create(PostDto dto) {
        if(dto.getOwner().getId() == null){
            dto.getOwner().setId(userRetrievalService.getCurrentUserId());
        }

        var entity = PostMapper.toEntity(new Post(), dto);
        return  toDto(repository.save(entity));
    }

    @Override
    public List<PostDto> getAll() {
        return repository.findAll().stream().map(this::toDto).toList();
    }

    @Override
    public PostDto update(String id, PostDto dto) {
        return toDto(repository.save(PostMapper
                .toEntity(repository.findById(id)
                        .orElseThrow(() -> new CoreException(MessageCodes.ENTITY_NOT_FOUND, Post.class.getSimpleName())), dto)));
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }

    @Override
    public List<PostDto> filter(PostDto post, String searchValue) {
        var cityId = post.getCity().getName() != null ? cityService.getIdByName(post.getCity().getName()) : null;

        if (searchValue != null && !searchValue.isEmpty()) {
            var animalTypeId = animalTypeService.getByName(searchValue).getId();
            return repository.filter(
                    post.getOwner().getId(),
                    post.getGender(),
                    post.getTitle(),
                    post.getDescription(),
                    animalTypeId,
                    post.getBreed().getId(),
                    cityId,
                    post.getDistrict().getId(),
                    true,
                    post.getStatus()).stream().map(this::toDto).toList();
        }
        return repository.filter(
                post.getOwner().getId(),
                post.getGender(),
                post.getTitle(),
                post.getDescription(),
                post.getAnimalType().getId(),
                post.getBreed().getId(),
                cityId,
                post.getDistrict().getId(),
                true,
                post.getStatus()).stream().map(this::toDto).toList();
    }

    @Override
    public List<PostDto> getAllByAnimalType(String animalType) {
        if(animalType.equals("Diğer")) {
            var dogId = animalTypeService.getByName("Köpek").getId();
            var catId = animalTypeService.getByName("Kedi").getId();
            return repository.findAllByAnimalTypeIdsNotIn(List.of(dogId, catId)).stream().map(this::toDto).toList();
        }

        var animalTypeId = animalTypeService.getByName(animalType).getId();
        return repository.findAllByAnimalTypeIdAndVerifiedIsTrue(animalTypeId).stream().map(this::toDto).toList();
    }

    @Override
    public PostDto getById(String id) {
        return toDto(repository.findById(id).orElseThrow(() -> new CoreException(MessageCodes.ENTITY_NOT_FOUND, Post.class.getSimpleName())));
    }

    @Override
    public List<PostDto> getAllByUserId(String id) {
        return repository.findAllByOwnerId(id).stream().map(this::toDto).toList();
    }

    @Override
    public List<PostDto> getMyPosts() {
        return repository.findAllByOwnerId(userRetrievalService.getCurrentUserId()).stream().map(this::toDto).toList();
    }

    public PostDto toDto(Post post) {
        return PostDto.builder()
                .id(post.getId())
                .name(post.getName())
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
                .status(post.getStatus() != null && post.getStatus())
                .city(cityService.getById(post.getCityId()))
                .district(districtService.getById(post.getDistrictId()))
                .mainImage(post.getMainImage())
                .images(post.getImages())
                .build();
    }
}
