package com.emrekentli.adoptme.domain.platform.post.web;

import com.emrekentli.adoptme.domain.platform.post.api.Gender;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class PostRequest {
    private final String ownerId;
    private final String title;
    private final String description;
    private final String animalTypeId;
    private final String animalBreedId;
    private final Integer age;
    private final Boolean verified;
    private final Boolean status;
    private final Gender gender;
    private final String cityId;
    private final String districtId;
    private final String mainImage;
    private final Set<String> images;
}