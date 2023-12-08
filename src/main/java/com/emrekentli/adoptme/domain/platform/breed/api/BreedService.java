package com.emrekentli.adoptme.domain.platform.breed.api;

import java.util.List;

public interface BreedService {
    BreedDto create(BreedDto dto);

    List<BreedDto> getAll();

    BreedDto update(String id, BreedDto dto);

    void delete(String id);
}
