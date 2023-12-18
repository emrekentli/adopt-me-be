package com.emrekentli.adoptme.domain.platform.post.impl;

import com.emrekentli.adoptme.domain.platform.post.api.PostDto;

public class PostMapper {
    private PostMapper() {
    }


    public static Post toEntity(Post post, PostDto dto) {
        post.setAge(dto.getAge());
        post.setAnimalBreedId(dto.getBreed().getId());
        post.setName(dto.getName());
        post.setCityId(dto.getCity().getId());
        post.setDescription(dto.getDescription());
        post.setDistrictId(dto.getDistrict().getId());
        post.setGender(dto.getGender());
        post.setOwnerId(dto.getOwner().getId());
        post.setAnimalTypeId(dto.getAnimalType().getId());
        post.setMainImage(dto.getMainImage());
        post.setImages(dto.getImages());
        post.setStatus(dto.getStatus() != null ? dto.getStatus() : true);
        post.setTitle(dto.getTitle());
        post.setVerified(dto.getVerified() != null ? dto.getVerified() : false);
        return post;
    }
}
