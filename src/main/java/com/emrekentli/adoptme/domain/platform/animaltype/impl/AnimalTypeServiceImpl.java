package com.emrekentli.adoptme.domain.platform.animaltype.impl;

import com.emrekentli.adoptme.domain.platform.animaltype.api.AnimalTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnimalTypeServiceImpl  implements AnimalTypeService {
    private final AnimalTypeRepository repository;
}
