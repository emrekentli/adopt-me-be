package com.emrekentli.adoptme.domain.platform.post.impl;

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

    @Override
    public PostDto create(PostDto dto) {
        return PostMapper.toDto(repository.save(PostMapper.toEntity(new Post(),dto)));
    }

    @Override
    public List<PostDto> getAll() {
        return repository.findAll().stream().map(PostMapper::toDto).toList();
    }

    @Override
    public PostDto update(String id, PostDto dto) {
        return PostMapper.toDto(repository.save(PostMapper
                .toEntity(repository.findById(id)
                        .orElseThrow( () -> new CoreException(MessageCodes.ENTITY_NOT_FOUND, Post.class.getSimpleName())),dto)));
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }
}
