package com.emrekentli.adoptme.domain.platform.post.api;

import com.emrekentli.adoptme.domain.authentication.user.api.UserDto;
import com.emrekentli.adoptme.domain.platform.animaltype.api.AnimalTypeDto;
import com.emrekentli.adoptme.domain.platform.breed.api.BreedDto;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
@Builder
public class PostDto {
    private final String id;
    private final Date created;
    private final Date modified;
    private final UserDto owner;
    private final String title;
    private final String description;
    private final BreedDto breed;
    private final Integer age;
    private final Boolean verified;
    private final Boolean status;
    private final String city;
    private final String district;
    private final String mainImage;
    private final Set<String> images;
}
