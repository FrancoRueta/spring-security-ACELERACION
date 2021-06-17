package com.franco.securityACELERACION.registration.token;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthTokenService {

    private final AuthTokenRepository authTokenRepository;

    public void saveConfirmationToken(AuthToken token){
        authTokenRepository.save(token);
    }
}
