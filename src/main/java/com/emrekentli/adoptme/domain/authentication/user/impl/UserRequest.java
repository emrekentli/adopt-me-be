package com.emrekentli.adoptme.domain.authentication.user.impl;

import com.emrekentli.adoptme.domain.authentication.user.api.UserDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRequest {
    @NotBlank(message = "validation.required.name")
    @NotNull(message = "validation.required.name")
    @NotEmpty(message = "validation.required.name")
    private final String fullName;
    private final String email;
    private final String phoneNumber;
    private final String password;


    public UserDto toDto() {
        return UserDto.builder()
                .fullName(fullName)
                .email(email)
                .phoneNumber(phoneNumber)
                .password(password)
                .build();
    }
}
