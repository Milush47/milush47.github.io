package com.example.app.events.listeners;

import com.example.app.events.OnRegistrationSuccessEvent;
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
public class RegistrationEmailListener extends EmailListener<OnRegistrationSuccessEvent> {
    public RegistrationEmailListener(
            TokenService    tokenService,
            EmailService    emailService,
            MessageSource   messageSource
    ) {
        super(tokenService, emailService, messageSource);
    }

    @Override
    protected String getRecipient(OnRegistrationSuccessEvent event) {
        return event.getUser().getEmail();
    }

    @Override
    protected String getSubject(OnRegistrationSuccessEvent event) {
        return event.getSubject();
    }

    @Override
    protected String getUrl(OnRegistrationSuccessEvent event) {
        Token token = createToken(event.getUser(), VerificationToken::new);

        return "/auth"                  +
                "/confirmRegistration"  +
                "?verificationToken="   +
                token.getToken();
    }

    @Override
    protected Locale getLocale(OnRegistrationSuccessEvent event) {
        return event.getLocale();
    }

    @Override
    protected String getMessageKey(OnRegistrationSuccessEvent event) {
        return event.getMessage();
    }

    @Override
    protected String getUserName(OnRegistrationSuccessEvent event) {
        return event.getUser().getFirstname();
    }
}
