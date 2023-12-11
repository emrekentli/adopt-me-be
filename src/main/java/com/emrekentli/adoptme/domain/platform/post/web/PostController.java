package com.emrekentli.adoptme.domain.platform.post.web;

import com.emrekentli.adoptme.domain.platform.post.api.PostDto;
import com.emrekentli.adoptme.domain.platform.post.api.PostMapper;
import com.emrekentli.adoptme.domain.platform.post.api.PostService;
import com.emrekentli.adoptme.library.rest.BaseController;
import com.emrekentli.adoptme.library.rest.DataResponse;
import com.emrekentli.adoptme.library.rest.MetaResponse;
import com.emrekentli.adoptme.library.rest.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/posts")
@RestController
public class PostController extends BaseController {
    private final PostService service;

    @PostMapping
    public Response<PostResponse> create(@RequestBody PostRequest request) {
        PostDto item = service.create(PostMapper.toDto(request));
        return respond(PostMapper.toResponse(item));
    }
    @GetMapping
    public Response<DataResponse<PostResponse>> getAll() {
        List<PostDto> items = service.getAll();
        return respond(PostMapper.toResponse(items));
    }

    @PutMapping("/{id}")
    public Response<PostResponse> update(
            @PathVariable String id,
            @RequestBody PostRequest request) {
        PostDto item = service.update(id, PostMapper.toDto(request));
        return respond(PostMapper.toResponse(item));
    }

    @DeleteMapping("/{id}")
    public Response<Void> delete(@PathVariable(value = "id") String id) {
        service.delete(id);
        return new Response<>(MetaResponse.success());
    }

}
