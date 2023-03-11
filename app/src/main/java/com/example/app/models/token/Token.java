package com.example.app.models.token;

import com.example.app.models.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type",
        discriminatorType = DiscriminatorType.STRING)
@Getter
@Setter
public abstract class Token {
    protected final static int EXPIRATION = 60 * 24;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Column(name = "token")
    protected String token;
    @OneToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    protected User user;
    @Column(name="created_date")
    protected Instant createdDate;
    @Column(name = "expiry_date")
    protected Instant expiryDate;

    @Column(name = "type", insertable = false, updatable = false)
    protected String type;

    public Token(String token, User user) {
        this.token = token;
        this.user = user;
        this.createdDate = Instant.now();
        this.expiryDate = calculateExpiryDate();
    }

    public Token() {}

    public boolean isExpired() {
        return Instant.now().isAfter(expiryDate);
    }

    protected Instant calculateExpiryDate() {
        return Instant.now().plus(EXPIRATION, ChronoUnit.MINUTES);
    }
}
