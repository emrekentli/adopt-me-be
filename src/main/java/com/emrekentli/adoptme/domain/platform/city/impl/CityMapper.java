package com.emrekentli.adoptme.domain.platform.city.impl;

import com.emrekentli.adoptme.domain.platform.city.api.CityDto;

public class CityMapper {

    private CityMapper() {
    }

    public static CityDto toDto(City city) {
        return CityDto.builder()
                .id(city.getId())
                .name(city.getName())
                .created(city.getCreated())
                .modified(city.getModified())
                .status(city.getStatus())
                .build();
    }

    public static City toEntity(City city,CityDto dto) {
        city.setStatus(dto.getStatus());
        city.setName(dto.getName());
        return city;
    }
}
