package com.emrekentli.adoptme.domain.authentication.auth.impl;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationRequest {
    private String userName;
    private String password;
}
