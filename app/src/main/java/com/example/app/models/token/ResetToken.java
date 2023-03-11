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
@Table(name = "reset_token")
@DiscriminatorValue("reset")
public class ResetToken extends Token {
    public ResetToken(String token, User user) {
        super(token, user);
    }
}
