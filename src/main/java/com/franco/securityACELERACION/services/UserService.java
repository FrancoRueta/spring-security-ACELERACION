package com.franco.securityACELERACION.services;


import com.franco.securityACELERACION.entities.User;
import com.franco.securityACELERACION.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        return userRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("No existe el usuario con email"+email));
    }

    public String signUpUser(User user){
        boolean exists = userRepository.findByEmail(user.getEmail()).isPresent();
        if (exists){
            throw new InvalidParameterException("Ya existe un usuario con dicho email.");
        }
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepository.save(user);
        //TODO: enviar confirmation token.
        return "funciono.";
    }
}
