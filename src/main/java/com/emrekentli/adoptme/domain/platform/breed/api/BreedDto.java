package com.emrekentli.adoptme.domain.platform.breed.api;

import com.emrekentli.adoptme.domain.platform.animaltype.api.AnimalTypeDto;
import com.emrekentli.adoptme.domain.platform.animaltype.impl.AnimalType;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
@Data
@Builder
public class BreedDto {
    private final String id;
    private final Date created;
    private final Date modified;
    private final String name;
    private final AnimalTypeDto animalType;
}
