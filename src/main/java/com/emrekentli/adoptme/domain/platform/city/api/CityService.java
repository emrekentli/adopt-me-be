package com.emrekentli.adoptme.domain.platform.city.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface CityService {
    Page<CityDto> getAll(Pageable pageable);

    Page<CityDto> filter(CityDto dto, Pageable pageable);

    CityDto getById(String id);

    CityDto save(CityDto dto);

    CityDto update(String id, CityDto dto);

    void delete(String id);
}
