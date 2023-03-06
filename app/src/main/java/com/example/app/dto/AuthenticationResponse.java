package com.example.app.dto;

import com.example.app.models.entities.User;
import lombok.Builder;

/*
    AuthenticationResponse is used for sending response when user has just authorized
 */
public record AuthenticationResponse(
        String token,
        UserResponse user
) {
    @Builder public AuthenticationResponse {}
}
