package com.emrekentli.adoptme.domain.platform.city.impl;

import com.emrekentli.adoptme.domain.platform.city.api.CityDto;

public class CityMapper {

    private CityMapper() {
    }

    public static CityDto toDto(City city) {
        return CityDto.builder()
                .id(city.getId())
                .created(city.getCreated())
                .modified(city.getModified())
                .name(city.getName())
                .status(city.getStatus())
                .build();
    }

    public static City toEntity(City tenant,CityDto tenantDto) {
        tenant.setName(tenantDto.getName());
        tenant.setStatus(tenantDto.getStatus());
        return tenant;
    }
}
