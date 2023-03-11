package com.example.app.services;

import com.example.app.dto.requests.AuthenticationRequest;
import com.example.app.dto.requests.RegisterRequest;
import com.example.app.dto.responses.AuthenticationResponse;
import com.example.app.dto.responses.RegistrationResponse;
import com.example.app.events.OnRegistrationSuccessEvent;
import com.example.app.events.ResetPasswordByEmailEvent;
import com.example.app.models.user.User;
import com.example.app.models.user.Role;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

@Service
@AllArgsConstructor
public class AuthenticationService {
    private final   PasswordEncoder             passwordEncoder;
    private final   JWTService                  jwtService;
    private final   AuthenticationManager       authManager;
    private final   UserService                 userService;
    private final   TokenService                tokenService;
    private         ApplicationEventPublisher   eventPublisher;

    public RegistrationResponse register(
            RegisterRequest registerRequest,
            WebRequest request
    ) {
        var user = User.builder()
                .firstname(registerRequest.firstname())
                .lastname(registerRequest.lastname())
                .email(registerRequest.email())
                .password(passwordEncoder.encode(registerRequest.password()))
                .enabled(true)
                .locked(false)
                .role(Role.USER)
                .build();

        userService.save(user);

        String appUrl = request.getContextPath();
        eventPublisher.publishEvent(new OnRegistrationSuccessEvent(user, request.getLocale(), appUrl));

        var     jwtToken            = jwtService.generateToken(user);

        return RegistrationResponse.builder()
                .JWT(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );

        var user        = userService.findByEmail(request.email());
        var jwtToken    = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .user(userService.mapToUserResponse(user))
                .build();
    }

    public void provideEmail(String email, WebRequest request) {
        User    user    = userService.findByEmail(email);
        String  appUrl  = request.getContextPath();

        eventPublisher.publishEvent(new ResetPasswordByEmailEvent(user, request.getLocale(), appUrl));
    }
}
