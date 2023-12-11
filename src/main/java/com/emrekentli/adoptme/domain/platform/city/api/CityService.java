package com.emrekentli.adoptme.domain.platform.city.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface CityService {
    Page<CityDto> filter(CityDto dto, Pageable pageable);

    CityDto create(CityDto dto);

    List<CityDto> getAll();

    CityDto update(String id, CityDto dto);

    void delete(String id);

    CityDto getById(String cityId);

}
