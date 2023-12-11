package com.emrekentli.adoptme.domain.platform.city.api;

import com.emrekentli.adoptme.domain.platform.animaltype.api.AnimalTypeDto;
import com.emrekentli.adoptme.domain.platform.city.web.CityRequest;
import com.emrekentli.adoptme.domain.platform.city.web.CityResponse;
import com.emrekentli.adoptme.library.util.PageUtil;
import org.springframework.data.domain.Page;

import java.util.List;

public class CityMapper {
    private CityMapper() {
    }

    public static CityDto toDto(CityRequest tenant) {
        return CityDto.builder()
                .name(tenant.getName())
                .status(tenant.getStatus())
                .build();
    }

    public static CityResponse toResponse(CityDto tenantDto) {
        return CityResponse.builder()
                .id(tenantDto.getId())
                .created(tenantDto.getCreated())
                .modified(tenantDto.getModified())
                .name(tenantDto.getName())
                .build();
    }

    public static Page<CityResponse> toPageResponse(Page<CityDto> tenantDtos) {
        return PageUtil.pageToDto(tenantDtos, CityMapper::toResponse);
    }

    public static List<CityResponse> toResponse(List<CityDto> tenantDtos) {
        return tenantDtos.stream().map(CityMapper::toResponse).toList();
    }

}
