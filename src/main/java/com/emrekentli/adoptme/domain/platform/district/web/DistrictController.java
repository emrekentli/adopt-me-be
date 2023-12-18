package com.emrekentli.adoptme.domain.platform.district.web;
import com.emrekentli.adoptme.domain.platform.city.api.CityDto;
import com.emrekentli.adoptme.domain.platform.district.api.DistrictDto;
import com.emrekentli.adoptme.domain.platform.district.api.DistrictMapper;
import com.emrekentli.adoptme.domain.platform.district.api.DistrictService;
import com.emrekentli.adoptme.library.rest.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("districts")
@RequiredArgsConstructor
public class DistrictController extends BaseController {

    private final DistrictService service;

    @GetMapping
    public Response<PageResponse<DistrictResponse>> getAll(Pageable pageable) {
        return respond(DistrictMapper.toPageResponse(service.getAll(pageable)));
    }

    @GetMapping("filter")
    public Response<DataResponse<DistrictResponse>> filter(@RequestParam(required = false) String name,
                                                           @RequestParam(required = false) Boolean status,
                                                           @RequestParam(required = false) String cityId) {
        DistrictDto dto = DistrictDto.builder()
                .name(name)
                .status(status)
                .city(CityDto.builder().id(cityId).build())
                .build();
        return respond(DistrictMapper.toResponse(service.filter(dto)));
    }

    @GetMapping("/{id}")
    public Response<DistrictResponse> getCityById(@PathVariable String id) {
        return respond(DistrictMapper.toResponse(service.getById(id)));
    }

    @PostMapping
    public Response<DistrictResponse> createCity(@RequestBody @Valid DistrictRequest request) {
        return respond(DistrictMapper.toResponse(service.save(DistrictMapper.toDto(request))));
    }

    @PutMapping("/{id}")
    public Response<DistrictResponse> updateCity(@PathVariable String id, @RequestBody DistrictRequest request) {
        return respond(DistrictMapper.toResponse(service.update(id, DistrictMapper.toDto(request))));
    }

    @DeleteMapping("/{id}")
    public Response<Void> deleteCity(@PathVariable String id) {
        service.delete(id);
        return new Response<>(MetaResponse.success());
    }
}
