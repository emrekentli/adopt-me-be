package com.emrekentli.adoptme.domain.platform.district.impl;

import com.emrekentli.adoptme.library.rest.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = District.TABLE)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class District extends AbstractEntity {
    public static final String TABLE = "district";
    private static final String COL_STATUS = "status";
    private static final String COL_NAME = "name";
    private static final String COL_CITY_ID = "city_id";
    private static final String COL_LONG = "longitude";
    private static final String COL_LAT = "latitude";


    @Column(name = COL_CITY_ID, nullable = false)
    private String cityId;

    @Column(name = COL_NAME, nullable = false)
    private String name;

    @Column(name = COL_STATUS)
    private Boolean status;

    @Column(name = COL_LONG)
    private Double longitude;

    @Column(name = COL_LAT)
    private Double latitude;

}
