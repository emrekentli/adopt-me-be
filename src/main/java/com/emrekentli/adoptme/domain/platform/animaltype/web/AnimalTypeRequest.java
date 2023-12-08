package com.emrekentli.adoptme.domain.platform.animaltype.web;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AnimalTypeRequest {
    private String name;

}