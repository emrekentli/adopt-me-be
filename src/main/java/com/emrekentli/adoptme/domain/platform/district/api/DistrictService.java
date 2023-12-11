package com.emrekentli.adoptme.domain.platform.district.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface DistrictService {
    Page<DistrictDto> getAll(Pageable pageable);

    Page<DistrictDto> filter(DistrictDto dto, Pageable pageable);

    DistrictDto getById(String id);

    DistrictDto save(DistrictDto dto);

    DistrictDto update(String id, DistrictDto dto);

    void delete(String id);
}
