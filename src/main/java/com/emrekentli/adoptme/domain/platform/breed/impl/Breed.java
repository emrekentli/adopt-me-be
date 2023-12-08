package com.emrekentli.adoptme.domain.platform.breed.impl;

import com.emrekentli.adoptme.library.entity.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = Breed.TABLE)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Breed extends AbstractEntity {
    public static final String TABLE = "breed";
    private static final String COL_NAME = "name";
    private static final String COL_ANIMAL_TYPE_ID = "animal_type_id";

    @Column(name = COL_NAME)
    private String name;

    @Column(name = COL_ANIMAL_TYPE_ID)
    private String animalTypeId;

}