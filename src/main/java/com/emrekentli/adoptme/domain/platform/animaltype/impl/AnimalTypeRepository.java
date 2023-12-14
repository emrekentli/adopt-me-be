package com.emrekentli.adoptme.domain.platform.animaltype.impl;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnimalTypeRepository extends JpaRepository<AnimalType, String> {
    Optional<AnimalType> findByNameIgnoreCase(String animal);
}
