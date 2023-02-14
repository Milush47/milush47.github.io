package com.example.app.services;

import com.example.app.errors.ExceptionMessage;
import com.example.app.events.OnRegistrationSuccessEvent;
import com.example.app.services.EmailService;
import com.example.app.dto.AuthenticationRequest;
import com.example.app.dto.ResetPasswordRequest;
import com.example.app.dto.AuthenticationResponse;
import com.example.app.dto.RegisterRequest;
import com.example.app.services.JWTService;
import com.example.app.models.repositories.UserRepository;
import com.example.app.models.entities.User;
import com.example.app.models.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final   UserRepository              userRepository;
    private final   PasswordEncoder             passwordEncoder;
    private final   JWTService                  jwtService;
    private final   AuthenticationManager       authManager;
    private final   EmailService                emailService;
    private         ApplicationEventPublisher   eventPublisher;

    public AuthenticationResponse register(
            RegisterRequest registerRequest,
            WebRequest request
    ) {
        var user = User.builder()
                .firstname(registerRequest.getFirstname())
                .lastname(registerRequest.getLastname())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.USER)
                .build();

        String appUrl = request.getContextPath();
        eventPublisher.publishEvent(new OnRegistrationSuccessEvent(user, request.getLocale(),appUrl));

        userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );



        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                String.format(ExceptionMessage.USER_NOT_FOUND, request.getEmail())
                        )
                );

        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse resetPassword(ResetPasswordRequest request) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getNewPassword()
                )
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                String.format(ExceptionMessage.USER_NOT_FOUND, request.getEmail())
                        )
                );

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
