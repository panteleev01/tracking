package com.example.tracking.controller;

import com.example.tracking.dao.RegistrationRequest;
import com.example.tracking.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/registration")
public class RegistrationController {

    private final RegistrationService service;

    @Autowired
    public RegistrationController(final RegistrationService service) {
        this.service = service;
    }

    @PostMapping
    public void register(@RequestBody RegistrationRequest request) {
        service.register(request);
    }

}
