package com.emrekentli.adoptme.domain.authentication.user.impl;


import com.emrekentli.adoptme.domain.authentication.role.impl.Role;
import com.emrekentli.adoptme.library.rest.AbstractEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = User.TABLE, uniqueConstraints = {
        @UniqueConstraint(columnNames = User.COL_EMAIL),
})
public class User extends AbstractEntity {
    public static final String TABLE = "usr";
    public static final String COL_PASSWORD = "password";
    public static final String COL_ACTIVITY = "activity";
    public static final String COL_FULL_NAME = "full_name";
    public static final String COL_EMAIL = "email";
    public static final String COL_PHONE_NUMBER = "phone_number";
    public static final String TABLE_USER_ROLE = "user_role";
    public static final String COL_ROLE_ID = "role_id";
    public static final String COL_USER_ID = "user_id";
    public static final String COL_IMAGE = "image";



    @Column(name = COL_FULL_NAME)
    private  String fullName;

    @Column(name = COL_IMAGE)
    private  String image;


    @Column(name = COL_EMAIL)
    private  String email;

    @Column(name = COL_PHONE_NUMBER)
    private  String phoneNumber;

    @Column(name = COL_PASSWORD)
    private String password;

    @Column(name = COL_ACTIVITY)
    private Boolean activity;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = TABLE_USER_ROLE,
            joinColumns = @JoinColumn(name = COL_USER_ID),
            inverseJoinColumns = @JoinColumn(name = COL_ROLE_ID)
    )
    private Set<Role> roles;
}
