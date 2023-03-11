package com.example.app.events.listeners;

import com.example.app.events.OnSuccessPasswordResettingEvent;
import com.example.app.events.ResetPasswordByEmailEvent;
import com.example.app.models.token.ResetToken;
import com.example.app.models.token.Token;
import com.example.app.models.token.VerificationToken;
import com.example.app.models.user.User;
import com.example.app.services.EmailService;
import com.example.app.services.TokenService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class SuccessPasswordResetListener extends EmailListener<OnSuccessPasswordResettingEvent> {

    public SuccessPasswordResetListener(
            TokenService    tokenService,
            EmailService    emailService,
            MessageSource   messageSource
    ) {
        super(tokenService, emailService, messageSource);
    }

    @Override
    protected String getRecipient(OnSuccessPasswordResettingEvent event) {
        return event.getUser().getEmail();
    }

    @Override
    protected String getSubject(OnSuccessPasswordResettingEvent event) {
        return event.getSubject();
    }

    @Override
    protected String getUrl(OnSuccessPasswordResettingEvent event) {

        return "/profile";

    }

    @Override
    protected Locale getLocale(OnSuccessPasswordResettingEvent event) {
        return event.getLocale();
    }

    @Override
    protected String getMessageKey(OnSuccessPasswordResettingEvent event) {
        return event.getMessage();
    }

    @Override
    protected String getUserName(OnSuccessPasswordResettingEvent event) {
        return event.getUser().getFirstname();
    }
}
