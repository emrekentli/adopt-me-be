package com.emrekentli.adoptme.domain.platform.post.impl;

import com.emrekentli.adoptme.domain.platform.post.api.Gender;
import com.emrekentli.adoptme.library.rest.AbstractEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = Post.TABLE)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Post extends AbstractEntity {
    public static final String TABLE = "post";
    public static final String OWNER_ID = "owner_id";
    public static final String TITLE = "title";
    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String ANIMAL_BREED_ID = "animal_breed_id";
    public static final String ANIMAL_TYPE_ID = "animal_type_id";
    public static final String AGE = "age";
    public static final String VERIFIED = "verified";
    public static final String STATUS = "status";
    public static final String CITY_ID = "city_id";
    public static final String DISTRICT_ID = "district_id";
    public static final String MAIN_IMAGE = "main_image";
    public static final String IMAGES = "images";
    public static final String GENDER = "gender";

    @Column(name = OWNER_ID)
    private String ownerId;

    @Column(name = GENDER)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = TITLE)
    private String title;

    @Column(name = NAME)
    private String name;

    @Column(name = DESCRIPTION)
    private String description;

    @Column(name = ANIMAL_BREED_ID)
    private String animalBreedId;

    @Column(name = ANIMAL_TYPE_ID)
    private String animalTypeId;

    @Column(name = AGE)
    private Integer age;

    @Column(name = VERIFIED)
    private Boolean verified;

    @Column(name = STATUS)
    private Boolean status;

    @Column(name = CITY_ID)
    private String cityId;

    @Column(name = DISTRICT_ID)
    private String districtId;

    @Column(name = MAIN_IMAGE,columnDefinition = "TEXT")
    private String mainImage;

    @Column(name = IMAGES)
    @ElementCollection
    private Set<String> images;
}