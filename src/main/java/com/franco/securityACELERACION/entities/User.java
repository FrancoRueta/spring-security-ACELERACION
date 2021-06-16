package com.franco.securityACELERACION.entities;

import com.franco.securityACELERACION.entities.DTOS.AppUserRole;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;


@Table(name="users")
@Entity
@Data
public class User implements UserDetails {
    @Id
    @SequenceGenerator(
            name= "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "post_sequence")
    @Column(name = "id",unique = true)
    private Long id;
    private String name;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private AppUserRole appUserRole;

    private Boolean locked = false;
    private Boolean enabled = false;

    public User(String name, String email, String password, AppUserRole appUserRole) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.appUserRole = appUserRole;
    }

    public User() {
    }

    public User(Long id, String name, String email, String password, AppUserRole appUserRole, Boolean locked, Boolean enabled) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.appUserRole = appUserRole;
        this.locked = locked;
        this.enabled = enabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(appUserRole.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
