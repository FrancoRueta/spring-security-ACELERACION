package com.franco.securityACELERACION.registration.token;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class AuthTokenService {

    private final AuthTokenRepository authTokenRepository;

    public void saveConfirmationToken(AuthToken token){
        authTokenRepository.save(token);
    }

    public int setConfirmedAt(String token) {
        return authTokenRepository.updateConfirmedAt(token, LocalDateTime.now());
    }
}
