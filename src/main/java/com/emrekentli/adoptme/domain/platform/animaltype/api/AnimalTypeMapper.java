package com.emrekentli.adoptme.domain.platform.animaltype.api;

import com.emrekentli.adoptme.domain.platform.animaltype.impl.AnimalType;
import com.emrekentli.adoptme.domain.platform.animaltype.web.AnimalTypeRequest;
import com.emrekentli.adoptme.domain.platform.animaltype.web.AnimalTypeResponse;
import com.emrekentli.adoptme.library.util.PageUtil;
import org.springframework.data.domain.Page;

import java.util.List;

public class AnimalTypeMapper {
    private AnimalTypeMapper() {
    }

    public static AnimalTypeDto toDto(AnimalTypeRequest tenant) {
        return AnimalTypeDto.builder()
                .name(tenant.getName())
                .build();
    }

    public static AnimalTypeResponse toResponse(AnimalTypeDto tenantDto) {
        return AnimalTypeResponse.builder()
                .id(tenantDto.getId())
                .created(tenantDto.getCreated())
                .modified(tenantDto.getModified())
                .name(tenantDto.getName())
                .build();
    }

    public static Page<AnimalTypeResponse> toPageResponse(Page<AnimalTypeDto> tenantDtos) {
        return PageUtil.pageToDto(tenantDtos, AnimalTypeMapper::toResponse);
    }

    public static List<AnimalTypeResponse> toResponse(List<AnimalTypeDto> tenantDtos) {
        return tenantDtos.stream().map(AnimalTypeMapper::toResponse).toList();
    }

}
