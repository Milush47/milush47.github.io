package com.example.app.models.token;

import com.example.app.models.user.User;

@FunctionalInterface
public interface TokenFactory<T extends Token> {
    T create(String token, User user);
}
