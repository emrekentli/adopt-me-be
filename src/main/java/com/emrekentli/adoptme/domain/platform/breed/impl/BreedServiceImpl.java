package com.emrekentli.adoptme.domain.platform.breed.impl;

import com.emrekentli.adoptme.domain.platform.breed.api.BreedDto;
import com.emrekentli.adoptme.domain.platform.breed.api.BreedService;
import com.emrekentli.adoptme.library.enums.MessageCodes;
import com.emrekentli.adoptme.library.exception.CoreException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BreedServiceImpl implements BreedService {
    private final BreedRepository repository;

    @Override
    public BreedDto create(BreedDto dto) {
        return BreedMapper.toDto(repository.save(BreedMapper.toEntity(new Breed(),dto)));
    }

    @Override
    public List<BreedDto> getAll() {
        return repository.findAll().stream().map(BreedMapper::toDto).toList();
    }

    @Override
    public BreedDto update(String id, BreedDto dto) {
        return BreedMapper.toDto(repository.save(BreedMapper
                .toEntity(repository.findById(id)
                        .orElseThrow( () -> new CoreException(MessageCodes.ENTITY_NOT_FOUND, Breed.class.getSimpleName())),dto)));
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }

    @Override
    public BreedDto getById(String animalBreedId) {
        return BreedMapper.toDto(repository.findById(animalBreedId)
                .orElseThrow( () -> new CoreException(MessageCodes.ENTITY_NOT_FOUND, Breed.class.getSimpleName(),animalBreedId)));
    }

    @Override
    public List<BreedDto> filterBreeds(BreedDto dto) {
        return repository.findAll(Example.of(BreedMapper.toEntity(new Breed(),dto))).stream().map(BreedMapper::toDto).toList();
    }
}
