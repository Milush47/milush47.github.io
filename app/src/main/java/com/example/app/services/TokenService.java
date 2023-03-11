package com.example.app.services;

import com.example.app.errors.InvalidTokenException;
import com.example.app.models.token.*;
import com.example.app.models.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final TokenRepository<Token> tokenRepository;

    public <T extends Token> T createToken(
            User            user,
            TokenFactory<T> tokenFactory
    ) {
        String tokenString = UUID.randomUUID().toString();

        T token = tokenFactory.create(tokenString, user);

        return tokenRepository.save(token);
    }

    public boolean isTokenValid(
            String tokenString,
            User user
    ) throws InvalidTokenException {
        Token token = tokenRepository.findByToken(tokenString)
                .orElseThrow(() -> new InvalidTokenException("Token is invalid"));

        if(token.isExpired()) {
            throw new InvalidTokenException("Token is expired");
        }

        return token.getUser().equals(user);
    }

    public Token findByToken(String tokenString) {

        return tokenRepository.findByToken(tokenString)
                .orElseThrow(() -> new InvalidTokenException("Token is invalid"));
    }

    public Token findByUser(User user, String type) {
        return tokenRepository.findByUserAndType(user, type)
                .orElseThrow(() -> new InvalidTokenException("Token is invalid"));
    }

    public void deleteToken(String tokenString) {
        Token token = findByToken(tokenString);

        tokenRepository.delete(token);
    }
}
