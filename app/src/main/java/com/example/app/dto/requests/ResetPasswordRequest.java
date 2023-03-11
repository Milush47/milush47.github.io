package com.example.app.dto.requests;

import com.example.app.validators.fieldMatch.FieldMatch;
import com.example.app.validators.password.ValidPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/*
    RegisterRequest is used for receiving data from resetPassword form
 */
@FieldMatch.List(
        @FieldMatch(
                first   = "newPassword",
                second  = "newPasswordConfirmation",
                message = "pswds.must.match"
        )
)
public record ResetPasswordRequest(
        @ValidPassword(message = "pswd.valid")
        @NotBlank(message = "pswd.is.req")
        String newPassword,

        @ValidPassword(message = "pswd.valid")
        @NotBlank(message = "pswd.conf.is.req")
        String newPasswordConfirmation
) {}
