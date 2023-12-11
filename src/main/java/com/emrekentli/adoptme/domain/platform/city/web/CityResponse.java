package com.emrekentli.adoptme.domain.platform.city.web;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@RequiredArgsConstructor
@Builder
@ToString
public class CityResponse {
    private final String id;
    private final Date created;
    private final Date modified;
    private final Boolean status;
    private final String name;
}
