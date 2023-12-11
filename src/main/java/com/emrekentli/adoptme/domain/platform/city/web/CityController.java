package com.emrekentli.adoptme.domain.platform.city.web;
import com.emrekentli.adoptme.domain.platform.city.api.CityDto;
import com.emrekentli.adoptme.domain.platform.city.api.CityMapper;
import com.emrekentli.adoptme.domain.platform.city.api.CityService;
import com.emrekentli.adoptme.library.rest.BaseController;
import com.emrekentli.adoptme.library.rest.MetaResponse;
import com.emrekentli.adoptme.library.rest.PageResponse;
import com.emrekentli.adoptme.library.rest.Response;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("cities")
@RequiredArgsConstructor
public class CityController extends BaseController {

    private final CityService service;

    @GetMapping
    public Response<PageResponse<CityResponse>> getAllCities(Pageable pageable) {
        return respond(CityMapper.toPageResponse(service.getAll(pageable)));
    }

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

    @GetMapping("/{id}")
    public Response<CityResponse> getCityById(@PathVariable String id) {
        return respond(CityMapper.toResponse(service.getById(id)));
    }

    @PostMapping
    public Response<CityResponse> createCity(@RequestBody @Valid CityRequest request) {
        return respond(CityMapper.toResponse(service.save(CityMapper.toDto(request))));
    }

    @PutMapping("/{id}")
    public Response<CityResponse> updateCity(@PathVariable String id, @RequestBody CityRequest request) {
        return respond(CityMapper.toResponse(service.update(id, CityMapper.toDto(request))));
    }

    @DeleteMapping("/{id}")
    public Response<Void> deleteCity(@PathVariable String id) {
        service.delete(id);
        return new Response<>(MetaResponse.success());
    }
}
