package com.example.app.dto.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record EmailRequest(
        @Email(message = "email.valid")
        @NotBlank(message = "email.is.req")
        @JsonProperty("email")
        String email
) {
}
