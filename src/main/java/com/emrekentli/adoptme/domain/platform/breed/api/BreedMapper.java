package com.emrekentli.adoptme.domain.platform.breed.api;

import com.emrekentli.adoptme.domain.platform.animaltype.api.AnimalTypeDto;
import com.emrekentli.adoptme.domain.platform.breed.web.BreedRequest;
import com.emrekentli.adoptme.domain.platform.breed.web.BreedResponse;
import com.emrekentli.adoptme.library.util.PageUtil;
import org.springframework.data.domain.Page;

import java.util.List;

public class BreedMapper {
    private BreedMapper() {
    }

    public static BreedDto toDto(BreedRequest breed) {
        return BreedDto.builder()
                .name(breed.getName())
                .animalType(AnimalTypeDto.builder().id(breed.getAnimalTypeId()).build())
                .build();
    }

    public static BreedResponse toResponse(BreedDto tenantDto) {
        return BreedResponse.builder()
                .id(tenantDto.getId())
                .created(tenantDto.getCreated())
                .modified(tenantDto.getModified())
                .name(tenantDto.getName())

                .build();
    }

    public static Page<BreedResponse> toPageResponse(Page<BreedDto> tenantDtos) {
        return PageUtil.pageToDto(tenantDtos, BreedMapper::toResponse);
    }

    public static List<BreedResponse> toResponse(List<BreedDto> tenantDtos) {
        return tenantDtos.stream().map(BreedMapper::toResponse).toList();
    }

}
