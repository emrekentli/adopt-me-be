package com.emrekentli.adoptme.domain.platform.animaltype.impl;

import com.emrekentli.adoptme.domain.platform.animaltype.api.AnimalTypeDto;
import com.emrekentli.adoptme.domain.platform.animaltype.api.AnimalTypeService;
import com.emrekentli.adoptme.library.enums.MessageCodes;
import com.emrekentli.adoptme.library.exception.CoreException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimalTypeServiceImpl  implements AnimalTypeService {
    private final AnimalTypeRepository repository;

    @Override
    public AnimalTypeDto create(AnimalTypeDto dto) {
        return AnimalTypeMapper.toDto(repository.save(AnimalTypeMapper.toEntity(new AnimalType(),dto)));
    }

    @Override
    public List<AnimalTypeDto> getAll() {
        return repository.findAll().stream().map(AnimalTypeMapper::toDto).toList();
    }

    @Override
    public AnimalTypeDto update(String id, AnimalTypeDto dto) {
        return AnimalTypeMapper.toDto(repository.save(AnimalTypeMapper
                .toEntity(repository.findById(id)
                        .orElseThrow( () -> new CoreException(MessageCodes.ENTITY_NOT_FOUND,AnimalType.class.getSimpleName())),dto)));
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }
}
