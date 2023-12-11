package com.emrekentli.adoptme.domain.platform.city.web;
import com.emrekentli.adoptme.domain.platform.city.api.CityDto;
import com.emrekentli.adoptme.domain.platform.city.api.CityMapper;
import com.emrekentli.adoptme.domain.platform.city.api.CityService;
import com.emrekentli.adoptme.library.rest.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cities")
@RequiredArgsConstructor
public class CityController extends BaseController {

    private final CityService service;
    @GetMapping("filter")
    public Response<PageResponse<CityResponse>> filter(@RequestParam(required = false) String name,
                                                       @RequestParam(required = false) Boolean status,
                                                       Pageable pageable) {
        CityDto dto = CityDto.builder()
                .name(name)
                .status(status)
                .build();
        return respond(CityMapper.toPageResponse(service.filter(dto, pageable)));
    }

    @PostMapping
    public Response<CityResponse> create(@RequestBody CityRequest request) {
        CityDto item = service.create(CityMapper.toDto(request));
        return respond(CityMapper.toResponse(item));
    }
    @GetMapping
    public Response<DataResponse<CityResponse>> getAll() {
        List<CityDto> items = service.getAll();
        return respond(CityMapper.toResponse(items));
    }

    @PutMapping("/{id}")
    public Response<CityResponse> update(
            @PathVariable String id,
            @RequestBody CityRequest request) {
        CityDto item = service.update(id, CityMapper.toDto(request));
        return respond(CityMapper.toResponse(item));
    }

    @DeleteMapping("/{id}")
    public Response<Void> delete(@PathVariable(value = "id") String id) {
        service.delete(id);
        return new Response<>(MetaResponse.success());
    }
}
