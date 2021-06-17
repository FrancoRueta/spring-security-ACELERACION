package com.franco.securityACELERACION.services;


import com.franco.securityACELERACION.entities.User;
import com.franco.securityACELERACION.registration.token.AuthToken;
import com.franco.securityACELERACION.registration.token.AuthTokenRepository;
import com.franco.securityACELERACION.registration.token.AuthTokenService;
import com.franco.securityACELERACION.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthTokenService authTokenService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        return userRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("No existe el usuario con email"+email));
    }

    public String signUpUser(User user){
        boolean exists = userRepository.findByEmail(user.getEmail()).isPresent();
        if (exists){
            return "El email se encuentra en uso.";
        }
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepository.save(user);
        String token = UUID.randomUUID().toString();
        AuthToken authToken = new AuthToken(token, LocalDateTime.now(),LocalDateTime.now().plusMinutes(15),user);
        authTokenService.saveConfirmationToken(authToken);
        //TODO: mandar email
        return token;
    }
}
