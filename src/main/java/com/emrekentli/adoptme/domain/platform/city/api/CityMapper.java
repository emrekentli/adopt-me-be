package com.emrekentli.adoptme.domain.platform.city.api;

import com.emrekentli.adoptme.domain.platform.city.web.CityRequest;
import com.emrekentli.adoptme.domain.platform.city.web.CityResponse;
import com.emrekentli.adoptme.library.util.PageUtil;
import org.springframework.data.domain.Page;

import java.util.List;

public class CityMapper {

    private CityMapper() {
    }

    public static CityDto toDto(CityRequest request) {
        return CityDto.builder()
                .status(request.getStatus())
                .name(request.getName())
                .build();
    }

    public static CityResponse toResponse(CityDto dto) {
        return CityResponse.builder()
                .id(dto.getId())
                .name(dto.getName())
                .created(dto.getCreated())
                .modified(dto.getModified())
                .status(dto.getStatus())
                .build();
    }

    public static Page<CityResponse> toPageResponse(Page<CityDto> cityDtos) {
        return PageUtil.pageToDto(cityDtos, CityMapper::toResponse);
    }

    public static List<CityResponse> toResponse(List<CityDto> cityDtos) {
        return cityDtos.stream().map(CityMapper::toResponse).toList();
    }
}
