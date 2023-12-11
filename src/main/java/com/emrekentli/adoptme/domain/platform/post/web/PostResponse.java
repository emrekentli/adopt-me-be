package com.emrekentli.adoptme.domain.platform.post.web;

import com.emrekentli.adoptme.domain.authentication.user.api.UserDto;
import com.emrekentli.adoptme.domain.platform.animaltype.api.AnimalTypeDto;
import com.emrekentli.adoptme.domain.platform.breed.api.BreedDto;
import com.emrekentli.adoptme.domain.platform.city.api.CityDto;
import com.emrekentli.adoptme.domain.platform.district.api.DistrictDto;
import com.emrekentli.adoptme.domain.platform.post.api.Gender;
import lombok.Builder;

import java.util.Date;
import java.util.Set;

public record PostResponse(String id, Date created, Date modified, UserDto owner, String title, String description, AnimalTypeDto animalType,
                           Gender gender, BreedDto breed, Integer age, Boolean verified, Boolean status, CityDto city, DistrictDto district, String mainImage, Set<String> images) {
    @Builder
    public PostResponse {
    }
}