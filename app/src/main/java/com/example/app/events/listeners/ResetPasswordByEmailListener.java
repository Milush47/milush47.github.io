package com.example.app.events.listeners;

import com.example.app.events.OnRegistrationSuccessEvent;
import com.example.app.events.ResetPasswordByEmailEvent;
import com.example.app.models.token.ResetToken;
import com.example.app.models.token.Token;
import com.example.app.models.token.VerificationToken;
import com.example.app.models.user.User;
import com.example.app.services.EmailService;
import com.example.app.services.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class ResetPasswordByEmailListener extends EmailListener<ResetPasswordByEmailEvent> {

    public ResetPasswordByEmailListener(
            TokenService    tokenService,
            EmailService    emailService,
            MessageSource   messageSource
    ) {
        super(tokenService, emailService, messageSource);
    }

    @Override
    protected String getRecipient(ResetPasswordByEmailEvent event) {
        return event.getUser().getEmail();
    }

    @Override
    protected String getSubject(ResetPasswordByEmailEvent event) {
        return event.getSubject();
    }

    @Override
    protected String getUrl(ResetPasswordByEmailEvent event) {
        Token token = createToken(event.getUser(), ResetToken::new);

        return "/auth"              +
                "/resetPassword"    +
                "?resetToken="      +
                token.getToken();
    }

    @Override
    protected Locale getLocale(ResetPasswordByEmailEvent event) {
        return event.getLocale();
    }

    @Override
    protected String getMessageKey(ResetPasswordByEmailEvent event) {
        return event.getMessage();
    }

    @Override
    protected String getUserName(ResetPasswordByEmailEvent event) {
        return event.getUser().getFirstname();
    }
}
