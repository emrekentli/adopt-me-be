package com.emrekentli.adoptme.domain.authentication.user.api;

import com.emrekentli.adoptme.domain.authentication.role.api.RoleDto;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Set;
@Data
@Builder
public class UserDto {
    private final String id;
    private final Date created;
    private final Date modified;
    private final String password;
    private final Boolean activity;
    private final String image;
    private final Set<RoleDto> roles;
    private final String fullName;
    private final String email;
    private final String phoneNumber;
}
