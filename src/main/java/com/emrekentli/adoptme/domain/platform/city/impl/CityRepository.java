package com.emrekentli.adoptme.domain.platform.city.impl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City, String>{
    Optional<City> findByNameIgnoreCase(String name);
}
