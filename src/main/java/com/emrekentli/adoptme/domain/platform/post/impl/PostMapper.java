package com.emrekentli.adoptme.domain.platform.post.impl;

import com.emrekentli.adoptme.domain.platform.post.api.PostDto;

public class PostMapper {
    private PostMapper() {
    }

    public static PostDto toDto(Post post) {
        return PostDto.builder()
                .id(post.getId())
                .created(post.getCreated())
                .modified(post.getModified())

                .build();
    }

    public static Post toEntity(Post post, PostDto dto) {
        post.setAge(dto.getAge());
        post.setAnimalBreedId(dto.getBreed().getId());
        post.setCityId(dto.getCity());
        post.setDescription(dto.getDescription());
        post.setDistrictId(dto.getDistrict());
        post.setMainImage(dto.getMainImage());
        post.setImages(dto.getImages());
        post.setStatus(dto.getStatus());
        post.setTitle(dto.getTitle());
        post.setVerified(dto.getVerified());
        return post;
    }
}
