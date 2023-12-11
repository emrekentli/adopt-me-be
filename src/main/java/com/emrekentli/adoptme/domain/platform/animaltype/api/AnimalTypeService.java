package com.emrekentli.adoptme.domain.platform.animaltype.api;

import java.util.List;

public interface AnimalTypeService {
    AnimalTypeDto create(AnimalTypeDto dto);

    List<AnimalTypeDto> getAll();

    AnimalTypeDto update(String id, AnimalTypeDto dto);

    void delete(String id);

    AnimalTypeDto getById(String animalTypeId);
}
