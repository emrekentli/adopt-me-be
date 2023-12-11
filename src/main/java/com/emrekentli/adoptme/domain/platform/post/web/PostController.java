package com.emrekentli.adoptme.domain.platform.post.web;

import com.emrekentli.adoptme.domain.authentication.user.api.UserDto;
import com.emrekentli.adoptme.domain.platform.animaltype.api.AnimalTypeDto;
import com.emrekentli.adoptme.domain.platform.breed.api.BreedDto;
import com.emrekentli.adoptme.domain.platform.city.api.CityDto;
import com.emrekentli.adoptme.domain.platform.district.api.DistrictDto;
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

    @GetMapping("/filter")
    public Response<DataResponse<PostResponse>> filter(@RequestParam(required = false) String title,
                                                       @RequestParam(required = false) String description,
                                                       @RequestParam(required = false) String cityId,
                                                       @RequestParam(required = false) String districtId,
                                                       @RequestParam(required = false) String breedId,
                                                       @RequestParam(required = false) String animalTypeId,
                                                       @RequestParam(required = false) Integer age,
                                                       @RequestParam(required = false) Boolean status,
                                                       @RequestParam(required = false) Boolean verified,
                                                       @RequestParam(required = false) String userId) {
        PostDto post = PostDto.builder()
                .title(title)
                .description(description)
                .city(CityDto.builder().id(cityId).build())
                .district(DistrictDto.builder().id(districtId).build())
                .breed(BreedDto.builder().id(breedId).build())
                .animalType(AnimalTypeDto.builder().id(animalTypeId).build())
                .age(age)
                .status(status)
                .verified(verified)
                .owner(UserDto.builder().id(userId).build())
                .build();
        List<PostDto> items = service.filter(post);
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
