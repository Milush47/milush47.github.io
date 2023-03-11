package com.example.app.dto.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

public record UserResponse(
        @JsonProperty("firstname")
        String firstname,
        @JsonProperty("lastname")
        String lastname,
        @JsonProperty("email")
        String email,
        @JsonProperty("confirmed")
        Boolean confirmed,
        @JsonProperty("preferences")
        String preferences

) {
    @Builder public UserResponse {}
}
