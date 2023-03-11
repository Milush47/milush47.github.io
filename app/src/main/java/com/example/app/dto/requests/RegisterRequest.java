package com.example.app.dto.requests;

import com.example.app.validators.fieldMatch.FieldMatch;
import com.example.app.validators.password.ValidPassword;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import static com.example.app.errors.ExceptionMessage.INVALID_INPUT;

/*
    RegisterRequest is used for receiving data from registration form
*/

@FieldMatch.List(
        @FieldMatch(
                first   = "password",
                second  = "confirmedPassword",
                message = "pswds.must.match"
        )
)
public record RegisterRequest(
        @NotBlank(message = "fn.is.req")
        @JsonProperty("firstname")
        String firstname,
        @NotBlank(message = "ln.is.req")
        @JsonProperty("lastname")
        String lastname,
        @Email(message = "email.valid")
        @NotBlank(message = "email.is.req")
        @JsonProperty("email")
        String email,
        @ValidPassword(message = "pswd.valid")
        @NotBlank(message = "pswd.is.req")
        @JsonProperty("password")
        String password,
        @NotBlank(message = "pswd.conf.is.req")
        @JsonProperty("confirmedPassword")
        String confirmedPassword
) {
        @Builder public RegisterRequest{}
}
