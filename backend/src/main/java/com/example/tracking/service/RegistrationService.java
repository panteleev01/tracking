package com.example.tracking.service;


import com.example.tracking.dao.RegistrationRequest;
import com.example.tracking.model.AppUser;
import com.example.tracking.model.AppUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrationService {

    private final AppUserService appUserService;

    @Autowired
    public RegistrationService(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @Transactional
    public void register(RegistrationRequest request) {
        // todo validate username and password
        boolean isValidRequest = true;
        // todo error handling
        if (!isValidRequest) throw new IllegalStateException("Invalid request");

        AppUser appUser = new AppUser(
                request.username(),
                request.password(),
                AppUserRole.USER
        );
        appUserService.signUpUser(appUser);
    }

}
