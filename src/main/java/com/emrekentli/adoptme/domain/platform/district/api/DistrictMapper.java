package com.emrekentli.adoptme.domain.platform.district.api;

import com.emrekentli.adoptme.domain.platform.city.api.CityDto;
import com.emrekentli.adoptme.domain.platform.city.web.CityRequest;
import com.emrekentli.adoptme.domain.platform.city.web.CityResponse;
import com.emrekentli.adoptme.domain.platform.district.web.DistrictRequest;
import com.emrekentli.adoptme.domain.platform.district.web.DistrictResponse;
import com.emrekentli.adoptme.library.util.PageUtil;
import org.springframework.data.domain.Page;

import java.util.List;

public class DistrictMapper {
    private DistrictMapper() {
    }

    public static DistrictDto toDto(DistrictRequest tenant) {
        return DistrictDto.builder()
                .name(tenant.getName())
                .status(tenant.getStatus())
                .city(CityDto.builder().id(tenant.getCityId()).build())
                .build();
    }

    public static DistrictResponse toResponse(DistrictDto tenantDto) {
        return DistrictResponse.builder()
                .id(tenantDto.getId())
                .created(tenantDto.getCreated())
                .modified(tenantDto.getModified())
                .name(tenantDto.getName())
                .build();
    }

    public static Page<DistrictResponse> toPageResponse(Page<DistrictDto> tenantDtos) {
        return PageUtil.pageToDto(tenantDtos, DistrictMapper::toResponse);
    }

    public static List<DistrictResponse> toResponse(List<DistrictDto> tenantDtos) {
        return tenantDtos.stream().map(DistrictMapper::toResponse).toList();
    }

}
