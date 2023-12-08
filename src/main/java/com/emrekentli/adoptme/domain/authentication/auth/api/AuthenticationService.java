package com.emrekentli.adoptme.domain.authentication.auth.api;

import com.emrekentli.adoptme.domain.authentication.auth.impl.AuthenticationRequest;
import com.emrekentli.adoptme.domain.authentication.auth.impl.AuthenticationResponse;
import com.emrekentli.adoptme.domain.authentication.user.impl.UserRequest;

public interface AuthenticationService {

    AuthenticationResponse login(AuthenticationRequest request);

    void register(UserRequest request);
}
