package com.example.app.events;

import com.example.app.models.token.Token;
import com.example.app.models.user.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

@Getter
@Setter
public class OnRegistrationSuccessEvent extends EmailEvent {
    public OnRegistrationSuccessEvent(
            User    user,
            Locale  locale,
            String  appUrl
    ) {
        super(user, locale, appUrl);
    }

    @Override
    public String getSubject() {
        return "Success Registration";
    }

    @Override
    public String getMessage() {
        return "message.registrationSuccessConfirmationLink";
    }

}
