package com.emrekentli.adoptme.domain.platform.animaltype.impl;

import com.emrekentli.adoptme.domain.platform.animaltype.api.AnimalTypeDto;

public class AnimalTypeMapper {
    private AnimalTypeMapper() {
    }

    public static AnimalTypeDto toDto(AnimalType animalType) {
        return AnimalTypeDto.builder()
                .id(animalType.getId())
                .created(animalType.getCreated())
                .modified(animalType.getModified())
                .name(animalType.getName())
                .build();
    }

    public static AnimalType toEntity(AnimalType tenant,AnimalTypeDto tenantDto) {
        tenant.setName(tenantDto.getName());
        return tenant;
    }
}
