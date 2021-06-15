package com.franco.securityACELERACION.entities.DTOS;


import lombok.Data;

@Data
public class RegistrationRequest {
    private final String username;
    private final String email;
    private final String password;
}
