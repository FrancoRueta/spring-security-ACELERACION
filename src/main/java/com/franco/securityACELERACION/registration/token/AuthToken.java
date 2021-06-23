package com.franco.securityACELERACION.registration.token;

import com.franco.securityACELERACION.entities.User;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Data
public class AuthToken {

    @Id
    @SequenceGenerator(
            name= "confirmation_token_sequence",
            sequenceName = "confirmation_token_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "confirmation_token_sequence")
    @Column(name = "id",unique = true)
    private Long id;
    @Column(nullable = false)
    private String token;
    @Column(name = "created_at",nullable = false)
    private LocalDateTime createdAt;
    @Column(name = "expires_at",nullable = false)
    private LocalDateTime expiresAt;
    @Column(name = "confirmed_at")
    private LocalDateTime confirmedAt;

    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "user_id"
    )
    private User user;

    public AuthToken() {
    }

    public AuthToken(String token, LocalDateTime createdAt, LocalDateTime expiresAt, User user) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.user = user;
    }
}
