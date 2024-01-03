package com.emrekentli.adoptme.domain.platform.district.impl;

import com.emrekentli.adoptme.domain.platform.city.api.CityService;
import com.emrekentli.adoptme.domain.platform.district.api.DistrictDto;

public class DistrictMapper {

    private DistrictMapper() {
    }

    public static DistrictDto toDto(District district, CityService cityService) {
        return DistrictDto.builder()
                .id(district.getId())
                .name(district.getName())
                .created(district.getCreated())
                .modified(district.getModified())
                .status(district.getStatus())
                .city(cityService.getById(district.getCityId()))
                .build();
    }

    public static District toEntity(District district, DistrictDto dto) {
        district.setStatus(dto.getStatus());
        district.setName(dto.getName());
        district.setCityId(dto.getCity().getId());
        return district;
    }
}
