package com.emrekentli.adoptme.domain.platform.breed.impl;

import com.emrekentli.adoptme.domain.platform.breed.api.BreedDto;

public class BreedMapper {
    private BreedMapper() {
    }

    public static BreedDto toDto(Breed breed) {
        return BreedDto.builder()
                .id(breed.getId())
                .created(breed.getCreated())
                .modified(breed.getModified())
                .name(breed.getName())
                .build();
    }

    public static Breed toEntity(Breed tenant, BreedDto tenantDto) {
        tenant.setName(tenantDto.getName());
        return tenant;
    }
}
