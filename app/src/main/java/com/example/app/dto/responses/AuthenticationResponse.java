package com.example.app.dto.responses;

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
