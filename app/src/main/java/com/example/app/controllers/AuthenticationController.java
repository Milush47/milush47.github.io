package com.example.app.controllers;

import com.example.app.dto.*;
import com.example.app.services.AuthenticationService;
import com.example.app.services.EmailService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;


import static com.example.app.errors.ExceptionMessage.EMAIl_IS_TAKEN;

/*
    AuthController is made for mapping following paths:
        - auth/register         (POST)
        - auth/authenticate     (POST)
        - auth/logout           (POST)
        - auth/reset-password   (POST)
    It is responsible for all actions with User on web side
 */
@RestController
@RequestMapping("/auth")
@Validated
@RequiredArgsConstructor
//@CrossOrigin(origins = "http://localhost:5173")
public class AuthenticationController {
    // Autowired variables using constructor
    private final AuthenticationService authService;
    private final EmailService          emailService;

    // responsible for registration new user.
    @PostMapping("/register")
    public ResponseEntity<SuccessResponse> register(
            @Valid @RequestBody RegisterRequest registerRequest,
            WebRequest request
    )  {

        String email = registerRequest.email();

        if(emailService.isEmailExists(email)) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    String.format(EMAIl_IS_TAKEN, email)
            );
        }

        RegistrationResponse response = authService.register(registerRequest, request);

        return ResponseEntity.ok(
                SuccessResponse.builder()
                        .message("User is registered")
                        .success(true)
                        .data(response)
                        .build()
        );
    }

    // responsible for authentication user (by jwt)
    @PostMapping("/authenticate")
    public ResponseEntity<SuccessResponse> authenticate(
            @Valid @RequestBody AuthenticationRequest request
    ) {

        AuthenticationResponse response = authService.authenticate(request);

        return ResponseEntity.ok(
                SuccessResponse.builder()
                        .message("User is authenticated")
                        .success(true)
                        .data(response)
                        .build()
        );
    }

    // responsible for log outing user
    @PostMapping("/logout")
    public ResponseEntity<SuccessResponse> logout(HttpServletRequest request) throws ServletException {
        request.logout();
        request.getSession().invalidate();

        return ResponseEntity.ok(
                SuccessResponse.builder()
                        .message("User is logged out")
                        .success(true)
                        .build()
        );
    }

    // responsible for reset user's password (new password is provided by user)
    @PostMapping("/reset-password")
    public ResponseEntity<SuccessResponse> resetPassword(
            @Valid @RequestBody ResetPasswordRequest request
    ) {

        return ResponseEntity.ok(
                SuccessResponse.builder()
                        .message("Password is reset")
                        .success(true)
                        .data(authService.resetPassword(request))
                        .build()
        );
    }
}
