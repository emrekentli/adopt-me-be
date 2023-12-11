package com.emrekentli.adoptme.domain.platform.post.api;

import com.emrekentli.adoptme.domain.authentication.user.api.UserDto;
import com.emrekentli.adoptme.domain.platform.animaltype.api.AnimalTypeDto;
import com.emrekentli.adoptme.domain.platform.breed.api.BreedDto;
import com.emrekentli.adoptme.domain.platform.post.web.PostRequest;
import com.emrekentli.adoptme.domain.platform.post.web.PostResponse;
import com.emrekentli.adoptme.library.util.PageUtil;
import org.springframework.data.domain.Page;

import java.util.List;

public class PostMapper {
    private PostMapper() {
    }

    public static PostDto toDto(PostRequest breed) {
        return PostDto.builder()
                .owner(UserDto.builder().id(breed.getOwnerId()).build())
                .title(breed.getTitle())
                .description(breed.getDescription())
                .breed(BreedDto.builder().id(breed.getAnimalBreedId()).build())
                .age(breed.getAge())
                .verified(breed.getVerified())
                .status(breed.getStatus())
                .city(breed.getCityId())
                .district(breed.getDistrictId())
                .mainImage(breed.getMainImage())
                .images(breed.getImages())
                .build();
    }

    public static PostResponse toResponse(PostDto post) {
        return PostResponse.builder()
                .id(post.getId())
                .created(post.getCreated())
                .modified(post.getModified())
                .owner(post.getOwner())
                .title(post.getTitle())
                .description(post.getDescription())
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
