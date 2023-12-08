package com.emrekentli.adoptme.domain.platform.breed.web;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BreedRequest {
    private String name;
    private String animalTypeId;
}