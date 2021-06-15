package com.franco.securityACELERACION.services;


import com.franco.securityACELERACION.entities.DTOS.RegistrationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {
    public String register(RegistrationRequest request) {
        return "works";
    }
}
