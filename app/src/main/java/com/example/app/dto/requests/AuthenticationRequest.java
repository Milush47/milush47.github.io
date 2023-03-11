package com.example.app.dto.requests;

import com.example.app.errors.ExceptionMessage;
import com.example.app.validators.password.ValidPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;


public record AuthenticationRequest(
        @Email(message = "email.valid")
        @NotBlank(message = "email.is.req")
        String email,
        @ValidPassword(message = "pswd.valid")
        @NotBlank(message = "pswd.is.req")
        String password
) {
        @Builder public AuthenticationRequest {}
}
