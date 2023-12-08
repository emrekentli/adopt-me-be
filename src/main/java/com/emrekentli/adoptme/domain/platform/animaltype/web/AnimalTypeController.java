package com.emrekentli.adoptme.domain.platform.animaltype.web;

import com.emrekentli.adoptme.domain.platform.animaltype.api.AnimalTypeDto;
import com.emrekentli.adoptme.domain.platform.animaltype.api.AnimalTypeMapper;
import com.emrekentli.adoptme.domain.platform.animaltype.api.AnimalTypeService;
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
public class AnimalTypeController  extends BaseController {
    private final AnimalTypeService service;

    @PostMapping
    public Response<AnimalTypeResponse> create(@RequestBody AnimalTypeRequest request) {
        AnimalTypeDto item = service.create(AnimalTypeMapper.toDto(request));
        return respond(AnimalTypeMapper.toResponse(item));
    }
    @GetMapping
    public Response<DataResponse<AnimalTypeResponse>> getAll() {
        List<AnimalTypeDto> items = service.getAll();
        return respond(AnimalTypeMapper.toResponse(items));
    }

    @PutMapping("/{id}")
    public Response<AnimalTypeResponse> update(
            @PathVariable String id,
            @RequestBody AnimalTypeRequest request) {
        AnimalTypeDto item = service.update(id, AnimalTypeMapper.toDto(request));
        return respond(AnimalTypeMapper.toResponse(item));
    }

    @DeleteMapping("/{id}")
    public Response<Void> delete(@PathVariable(value = "id") String id) {
        service.delete(id);
        return new Response<>(MetaResponse.success());
    }

}
