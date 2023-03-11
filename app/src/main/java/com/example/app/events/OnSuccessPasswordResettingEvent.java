package com.example.app.events;

import com.example.app.models.token.Token;
import com.example.app.models.user.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Locale;

@Getter
@Setter
public class OnSuccessPasswordResettingEvent extends EmailEvent {
    public OnSuccessPasswordResettingEvent(
            User    user,
            Locale  locale,
            String  appUrl
    ) {
        super(user, locale, appUrl);
    }

    @Override
    public String getSubject() {
        return "Password reset successfully";
    }

    @Override
    public String getMessage() {
        return "message.passwordSuccessfullyReset";
    }

}
