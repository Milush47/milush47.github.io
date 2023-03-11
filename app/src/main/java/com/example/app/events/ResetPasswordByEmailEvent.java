package com.example.app.events;

import com.example.app.models.token.Token;
import com.example.app.models.user.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

@Getter
@Setter
public class ResetPasswordByEmailEvent extends EmailEvent {
    public ResetPasswordByEmailEvent(
            User    user,
            Locale  locale,
            String  appUrl
    ) {
        super(user, locale, appUrl);
    }

    @Override
    public String getSubject() {
        return "Reset password";
    }

    @Override
    public String getMessage() {
        return "message.resetPasswordLink";
    }
}
