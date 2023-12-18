package com.emrekentli.adoptme.domain.platform.district.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface DistrictService {
    Page<DistrictDto> getAll(Pageable pageable);

    List<DistrictDto> filter(DistrictDto dto);

    DistrictDto getById(String id);

    DistrictDto save(DistrictDto dto);

    DistrictDto update(String id, DistrictDto dto);

    void delete(String id);
}
