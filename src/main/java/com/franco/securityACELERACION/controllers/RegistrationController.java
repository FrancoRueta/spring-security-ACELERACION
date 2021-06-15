package com.franco.securityACELERACION.controllers;


import com.franco.securityACELERACION.entities.DTOS.RegistrationRequest;
import com.franco.securityACELERACION.services.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping(path = "/auth/")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    public String register(@RequestBody RegistrationRequest request){
        return registrationService.register(request);
    }
}
