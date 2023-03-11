package com.example.app.services;

import com.example.app.dto.requests.ResetPasswordRequest;
import com.example.app.dto.requests.UserRequest;
import com.example.app.dto.responses.UserResponse;
import com.example.app.errors.ExceptionMessage;
import com.example.app.errors.InvalidTokenException;
import com.example.app.events.OnRegistrationSuccessEvent;
import com.example.app.events.OnSuccessPasswordResettingEvent;
import com.example.app.models.user.User;
import com.example.app.models.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import java.util.Objects;

@AllArgsConstructor
@Service
public class UserService implements UserDetailsService {
    private final           UserRepository                  userRepository;
    private final           JWTService                      jwtService;
    private final           StorageService                  storageService;
    private final           TokenService                    tokenService;
    private final           PasswordEncoder                 passwordEncoder;
    private                 ApplicationEventPublisher       eventPublisher;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                String.format(ExceptionMessage.USER_NOT_FOUND, email))
                );
    }

    public User getUserByJWT(WebRequest request) {
        String token = (
                Objects.requireNonNull(
                        request.getHeader("Authorization")
                )
        ).substring(7);

        String  email   = jwtService.extractUsername(token);

        return findByEmail(email);

    }

    public void save(User user) {
        userRepository.save(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                String.format(ExceptionMessage.USER_NOT_FOUND, email)
                        )
                );
    }

    public boolean isPresent(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public void updateUser(
            User        user,
            UserRequest userRequest,
            WebRequest  request
    ) {
        if(!userRequest.firstname().isEmpty()) {
            user.setFirstname(userRequest.firstname());
        }

        if(!userRequest.firstname().isEmpty()) {
            user.setLastname(userRequest.lastname());
        }

        if(!userRequest.firstname().isEmpty()) {
            String appUrl = request.getContextPath();
            eventPublisher.publishEvent(new OnRegistrationSuccessEvent(user, request.getLocale(), appUrl));

            user.setEmail(userRequest.email());
        }

        if(!userRequest.avatar().isEmpty()) {
            storageService.uploadImage(userRequest.avatar(), user);
        }

        userRepository.save(user);
    }

    public UserResponse mapToUserResponse(User user) {
        return UserResponse.builder()
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .confirmed(user.isConfirmed())
                .preferences(user.getPreferences())
                .build();
    }

    public UserResponse completeVerification(String token, User user) {
        if(tokenService.isTokenValid(token, user)) {
            user.setConfirmed(true);

            save(user);

            return mapToUserResponse(user);
        }

        throw new InvalidTokenException("Verification token is invalid");
    }

    public UserResponse completePasswordResetting(
            String                  token,
            User                    user,
            ResetPasswordRequest    request,
            WebRequest              webRequest
            ) {
        if(tokenService.isTokenValid(token, user)) {
            user.setPassword(passwordEncoder.encode(request.newPassword()));

            save(user);

            String appUrl = webRequest.getContextPath();
            eventPublisher.publishEvent(new OnSuccessPasswordResettingEvent(user, webRequest.getLocale(), appUrl));

            return mapToUserResponse(user);
        }

        throw new InvalidTokenException("Reset token is invalid");
    }

    public void delete(User user) {
        userRepository.delete(user);
    }
}
