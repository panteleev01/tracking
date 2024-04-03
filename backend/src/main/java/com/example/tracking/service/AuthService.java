package com.example.tracking.service;

import com.example.tracking.dao.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;


@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @Autowired
    public AuthService(final AuthenticationManager authenticationManager, final TokenService tokenService) {
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
    }

    public String loginUser(LoginRequest loginRequest) {
        final Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.username(),
                        loginRequest.password()
                )
        );
        return tokenService.generateToken(auth);
    }



}
