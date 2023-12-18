package com.emrekentli.adoptme.domain.platform.district.impl;
import com.emrekentli.adoptme.domain.platform.city.api.CityService;
import com.emrekentli.adoptme.domain.platform.district.api.DistrictDto;
import com.emrekentli.adoptme.domain.platform.district.api.DistrictService;
import com.emrekentli.adoptme.library.enums.MessageCodes;
import com.emrekentli.adoptme.library.exception.CoreException;
import com.emrekentli.adoptme.library.util.PageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.emrekentli.adoptme.domain.platform.district.impl.DistrictMapper.toEntity;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DistrictServiceImpl implements DistrictService {
    private final DistrictRepository repository;
    private final CityService cityService;
    @Override
    public Page<DistrictDto> getAll(Pageable pageable) {
        return PageUtil.pageToDto(repository.findAll(pageable), district -> DistrictMapper.toDto(district,cityService));
    }
    @Override
    public DistrictDto getById(String id) {
        return repository.findById(id).map(district -> DistrictMapper.toDto(district,cityService)).orElseThrow(() -> new CoreException(MessageCodes.ENTITY_NOT_FOUND, District.class.getSimpleName(),id));
    }
    @Override
    @Transactional
    public DistrictDto save(DistrictDto dto) {
        return DistrictMapper.toDto(repository.save(toEntity(new District(),dto)),cityService);
    }
    @Override
    @Transactional
    public DistrictDto update(String id, DistrictDto dto) {
        District district =  repository.findById(id).orElseThrow(() -> new CoreException(MessageCodes.ENTITY_NOT_FOUND, District.class.getSimpleName(),id));
        return DistrictMapper.toDto(repository.save(setDistrict(district,dto)),cityService);
    }
    @Override
    @Transactional
    public void delete(String id) {
        var city = repository.findById(id).orElseThrow(() -> new CoreException(MessageCodes.ENTITY_NOT_FOUND, District.class.getSimpleName(),id));
        repository.delete(city);
    }
    @Override
    public List<DistrictDto> filter(DistrictDto dto) {
        return (repository.findAll(Example.of(toEntity(new District(),dto)))).stream().map(district -> DistrictMapper.toDto(district,cityService)).toList();
    }

    private District setDistrict(District district, DistrictDto dto) {
        district.setStatus(dto.getStatus());
        district.setName(dto.getName());
        district.setCityId(dto.getCity().getId());
        return district;
    }
}
