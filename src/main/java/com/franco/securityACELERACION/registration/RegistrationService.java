package com.franco.securityACELERACION.registration;


import com.franco.securityACELERACION.entities.DTOS.AppUserRole;
import com.franco.securityACELERACION.email.EmailValidator;
import com.franco.securityACELERACION.entities.User;
import com.franco.securityACELERACION.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final EmailValidator emailValidator;
    private final UserService userService;

    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if (!isValidEmail){
            throw new InvalidParameterException("El email es invalido."); }
        return userService.signUpUser(
                new User(
                        request.getUsername(),
                        request.getEmail(),
                        request.getPassword(),
                        AppUserRole.USER
                )
                );
    }
}
