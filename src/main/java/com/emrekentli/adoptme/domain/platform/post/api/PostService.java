package com.emrekentli.adoptme.domain.platform.post.api;

import java.util.List;

public interface PostService {
    PostDto create(PostDto dto);

    List<PostDto> getAll();

    PostDto update(String id, PostDto dto);

    void delete(String id);

    List<PostDto> filter(PostDto post);

    List<PostDto> getAllByAnimalType(String animalType);

    PostDto getById(String id);

    List<PostDto> getAllByUserId(String id);

    List<PostDto> getMyPosts();
}
