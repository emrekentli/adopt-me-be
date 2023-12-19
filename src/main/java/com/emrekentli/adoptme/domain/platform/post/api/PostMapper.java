package com.emrekentli.adoptme.domain.platform.post.api;

import com.emrekentli.adoptme.domain.authentication.user.api.UserDto;
import com.emrekentli.adoptme.domain.platform.animaltype.api.AnimalTypeDto;
import com.emrekentli.adoptme.domain.platform.breed.api.BreedDto;
import com.emrekentli.adoptme.domain.platform.city.api.CityDto;
import com.emrekentli.adoptme.domain.platform.district.api.DistrictDto;
import com.emrekentli.adoptme.domain.platform.post.web.PostRequest;
import com.emrekentli.adoptme.domain.platform.post.web.PostResponse;
import com.emrekentli.adoptme.library.util.PageUtil;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class PostMapper {
    private PostMapper() {
    }

    public static PostDto toDto(PostRequest breed) {
        return PostDto.builder()
                .owner(UserDto.builder().id(breed.getOwnerId()).build())
                .title(breed.getTitle())
                .description(breed.getDescription())
                .name(breed.getName())
                .breed(BreedDto.builder().id(breed.getAnimalBreedId()).build())
                .age(breed.getAge())
                .verified(breed.getVerified())
                .gender(breed.getGender())
                .animalType(AnimalTypeDto.builder().id(breed.getAnimalTypeId()).build())
                .status(breed.getStatus())
                .city(CityDto.builder().id(breed.getCityId()).build())
                .district(DistrictDto.builder().id(breed.getDistrictId()).build())
                .images(breed.getImages())
                .mainImage(breed.getMainImage())
                .build();
    }

    public static PostResponse toResponse(PostDto post) {
        return PostResponse.builder()
                .id(post.getId())
                .created(post.getCreated())
                .modified(post.getModified())
                .owner(post.getOwner())
                .name(post.getName())
                .title(post.getTitle())
                .description(post.getDescription())
                .animalType(post.getAnimalType())
                .gender(post.getGender())
                .breed(post.getBreed())
                .age(post.getAge())
                .verified(post.getVerified())
                .status(post.getStatus())
                .city(post.getCity())
                .district(post.getDistrict())
                .mainImage(post.getMainImage())
                .images(post.getImages())
                .build();
    }

    public static Page<PostResponse> toPageResponse(Page<PostDto> tenantDtos) {
        return PageUtil.pageToDto(tenantDtos, PostMapper::toResponse);
    }

    public static List<PostResponse> toResponse(List<PostDto> tenantDtos) {
        return tenantDtos.stream().map(PostMapper::toResponse).toList();
    }

}
