package com.franco.securityACELERACION.registration;


import lombok.Data;

@Data
public class RegistrationRequest {
    private final String username;
    private final String email;
    private final String password;
}
