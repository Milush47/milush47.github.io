package com.example.app.controllers;

import com.example.app.dto.requests.AuthenticationRequest;
import com.example.app.dto.requests.EmailRequest;
import com.example.app.dto.requests.RegisterRequest;
import com.example.app.dto.requests.ResetPasswordRequest;
import com.example.app.dto.responses.*;
import com.example.app.errors.InvalidTokenException;
import com.example.app.models.user.User;
import com.example.app.services.AuthenticationService;
import com.example.app.services.EmailService;
import com.example.app.services.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
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
public class AuthenticationController {
    // Autowired variables using constructor
    private final AuthenticationService authService;
    private final EmailService          emailService;
    private final UserService           userService;

    // responsible for registration new user.
    @PostMapping("/register")
    public ResponseEntity<SuccessResponse> register(
            @Valid @RequestBody RegisterRequest registerRequest,
                                WebRequest      request
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

    @PostMapping("/provideEmail")
    public ResponseEntity<SuccessResponse> provideEmail(
            @RequestBody    EmailRequest emailRequest,
                            WebRequest  request
    ) {

        authService.provideEmail(emailRequest.email(), request);

        return ResponseEntity.ok(
                SuccessResponse.builder()
                        .message("Reset message has been sent")
                        .success(true)
                        .build()
        );
    }

    // responsible for reset user's password (new password is provided by user)
    @PostMapping("/resetPassword")
    public ResponseEntity<SuccessResponse> resetPassword(
            @Valid  @RequestBody                ResetPasswordRequest    request,
                    @RequestParam("resetToken") String                  resetToken,
                                                WebRequest              webRequest
    ) {
        User user = userService.getUserByJWT(webRequest);

        UserResponse response = userService.completePasswordResetting(
                resetToken,
                user,
                request,
                webRequest
        );

        return ResponseEntity.ok(
                SuccessResponse.builder()
                        .message("Password is reset")
                        .success(true)
                        .data(response)
                        .build()
        );
    }

    @PostMapping("/confirmRegistration")
    public ResponseEntity<SuccessResponse> verifyEmail(
            @RequestParam("verificationToken")  String      verificationToken,
                                                WebRequest  request
    ) throws InvalidTokenException {
        User user = userService.getUserByJWT(request);

        UserResponse response = userService.completeVerification(verificationToken, user);

        return ResponseEntity.ok(
                SuccessResponse.builder()
                        .message("Email is verified")
                        .success(true)
                        .data(response)
                        .build()
        );
    }
}
