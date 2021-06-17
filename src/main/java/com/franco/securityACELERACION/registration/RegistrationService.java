package com.franco.securityACELERACION.registration;


import com.franco.securityACELERACION.entities.DTOS.AppUserRole;
import com.franco.securityACELERACION.email.EmailValidator;
import com.franco.securityACELERACION.entities.User;
import com.franco.securityACELERACION.registration.token.AuthToken;
import com.franco.securityACELERACION.registration.token.AuthTokenRepository;
import com.franco.securityACELERACION.registration.token.AuthTokenService;
import com.franco.securityACELERACION.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.InvalidParameterException;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final EmailValidator emailValidator;
    private final UserService userService;
    private final AuthTokenService authTokenService;
    private final AuthTokenRepository authTokenRepository;

    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if (!isValidEmail) {
            throw new InvalidParameterException("El email es invalido.");
        }
        return userService.signUpUser(
                new User(
                        request.getUsername(),
                        request.getEmail(),
                        request.getPassword(),
                        AppUserRole.USER
                )
        );
    }

    @Transactional
    public String confirmToken(String token) {
        AuthToken authToken = authTokenRepository.getByToken(token).orElseThrow(() ->
                new IllegalStateException("No se encontro el token."));

        if (authToken.getConfirmedAt() != null){
            throw new IllegalStateException("Ya se ha confirmado.");
        }

        LocalDateTime expiredAt = authToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())){
            throw new IllegalStateException("Tiempo de autenticacion superado.");
        }

        authTokenService.setConfirmedAt(token);
        userService.enableUser(authToken.getUser().getEmail());
        return "Ha sido confirmado.";
    }

}
