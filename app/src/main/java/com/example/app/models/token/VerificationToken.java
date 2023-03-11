package com.example.app.models.token;

import com.example.app.models.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "verification_token")
@DiscriminatorValue("verification")
public class VerificationToken extends Token {
    public VerificationToken(String token, User user) {
        super(token, user);
    }
}
