package com.emrekentli.adoptme.domain.platform.animaltype.web;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AnimalTypeRequest {
    private String name;

    public AnimalTypeRequest(@JsonProperty("name") String name) {
        this.name = name;
    }
}