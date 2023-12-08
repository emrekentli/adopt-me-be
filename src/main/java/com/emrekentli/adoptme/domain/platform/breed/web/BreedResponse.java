package com.emrekentli.adoptme.domain.platform.breed.web;

import com.emrekentli.adoptme.domain.platform.animaltype.api.AnimalTypeDto;
import lombok.Builder;

import java.util.Date;

public record BreedResponse(String id, Date created, Date modified, String name, AnimalTypeDto animalType) {
    @Builder
    public BreedResponse {
    }
}