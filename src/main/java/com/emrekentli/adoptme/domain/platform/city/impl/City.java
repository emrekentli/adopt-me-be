package com.emrekentli.adoptme.domain.platform.city.impl;

import com.emrekentli.adoptme.library.rest.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = City.TABLE)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class City extends AbstractEntity {
    public static final String TABLE = "city";
    private static final String COL_STATUS = "status";
    private static final String COL_NAME = "name";

    @Column(name = COL_NAME, nullable = false)
    private String name;

    @Column(name = COL_STATUS)
    private Boolean status;

}
