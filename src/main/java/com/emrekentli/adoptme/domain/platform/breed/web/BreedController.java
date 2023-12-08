package com.emrekentli.adoptme.domain.platform.breed.web;

import com.emrekentli.adoptme.domain.platform.breed.api.BreedDto;
import com.emrekentli.adoptme.domain.platform.breed.api.BreedMapper;
import com.emrekentli.adoptme.domain.platform.breed.api.BreedService;
import com.emrekentli.adoptme.library.rest.BaseController;
import com.emrekentli.adoptme.library.rest.DataResponse;
import com.emrekentli.adoptme.library.rest.MetaResponse;
import com.emrekentli.adoptme.library.rest.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/animal-types")
@RestController
public class BreedController extends BaseController {
    private final BreedService service;

    @PostMapping
    public Response<BreedResponse> create(@RequestBody BreedRequest request) {
        BreedDto item = service.create(BreedMapper.toDto(request));
        return respond(BreedMapper.toResponse(item));
    }
    @GetMapping
    public Response<DataResponse<BreedResponse>> getAll() {
        List<BreedDto> items = service.getAll();
        return respond(BreedMapper.toResponse(items));
    }

    @PutMapping("/{id}")
    public Response<BreedResponse> update(
            @PathVariable String id,
            @RequestBody BreedRequest request) {
        BreedDto item = service.update(id, BreedMapper.toDto(request));
        return respond(BreedMapper.toResponse(item));
    }

    @DeleteMapping("/{id}")
    public Response<Void> delete(@PathVariable(value = "id") String id) {
        service.delete(id);
        return new Response<>(MetaResponse.success());
    }

}
