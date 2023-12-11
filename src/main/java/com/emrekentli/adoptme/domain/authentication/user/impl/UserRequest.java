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
    @NotBlank(message = "validation.required.userName")
    @NotNull(message = "validation.required.userName")
    @NotEmpty(message = "validation.required.userName")
    private final String userName;
    @NotBlank(message = "validation.required.name")
    @NotNull(message = "validation.required.name")
    @NotEmpty(message = "validation.required.name")
    private final String fullName;
    private final String email;
    private final String phoneNumber;
    private final String image;


    public UserDto toDto() {
        return UserDto.builder()
                .userName(userName)
                .fullName(fullName)
                .email(email)
                .phoneNumber(phoneNumber)
                .image(image)
                .build();
    }
}
