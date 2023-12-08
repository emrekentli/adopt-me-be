package com.emrekentli.adoptme.domain.platform.animaltype.impl;

import com.emrekentli.adoptme.library.entity.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = AnimalType.TABLE)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AnimalType extends AbstractEntity {
    public static final String TABLE = "animal_type";
    private static final String COL_NAME = "name";

    @Column(name = COL_NAME)
    private String name;
}