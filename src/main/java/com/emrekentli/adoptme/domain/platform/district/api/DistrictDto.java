package com.emrekentli.adoptme.domain.platform.district.api;

import com.emrekentli.adoptme.domain.platform.city.api.CityDto;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@RequiredArgsConstructor
@Builder
@ToString
public class DistrictDto {
    private final String id;
    private final Date created;
    private final Date modified;
    private final CityDto city;
    private final Boolean status;
    private final String name;
}
