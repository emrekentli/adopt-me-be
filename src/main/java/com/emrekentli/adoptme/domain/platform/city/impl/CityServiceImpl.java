package com.emrekentli.adoptme.domain.platform.city.impl;

import com.emrekentli.adoptme.domain.authentication.user.impl.User;
import com.emrekentli.adoptme.domain.platform.city.api.CityDto;
import com.emrekentli.adoptme.domain.platform.city.api.CityService;
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

import static com.emrekentli.adoptme.domain.platform.city.impl.CityMapper.toEntity;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CityServiceImpl implements CityService {
    private final CityRepository repository;

    @Override
    public Page<CityDto> filter(CityDto dto, Pageable pageable) {
        return PageUtil.pageToDto(repository.findAll(Example.of(toEntity(new City(), dto)), pageable), CityMapper::toDto);
    }

    @Override
    @Transactional
    public CityDto create(CityDto dto) {
        return CityMapper.toDto(repository.save(toEntity(new City(), dto)));
    }

    @Override
    public List<CityDto> getAll() {
        return repository.findAll().stream().map(CityMapper::toDto).toList();
    }

    @Override
    @Transactional
    public CityDto update(String id, CityDto dto) {
        City city = repository.findById(id).orElseThrow(() -> new CoreException(MessageCodes.ENTITY_NOT_FOUND, City.class.getSimpleName(), id));
        return CityMapper.toDto(repository.save(toEntity(city, dto)));
    }

    @Override
    public void delete(String id) {
        repository.delete(repository.findById(id).orElseThrow(() -> new CoreException(MessageCodes.ENTITY_NOT_FOUND, City.class.getSimpleName(), id)));
    }

    @Override
    public CityDto getById(String cityId) {
        return CityMapper.toDto(repository.findById(cityId).orElseThrow(() -> new CoreException(MessageCodes.ENTITY_NOT_FOUND, City.class.getSimpleName(), cityId)));
    }

}
