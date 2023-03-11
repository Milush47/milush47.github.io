package com.example.app.events;

import com.example.app.models.token.Token;
import com.example.app.models.user.User;
import com.example.app.services.EmailService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.MessageSource;

import java.util.Locale;

@Getter
@Setter
public abstract class EmailEvent extends ApplicationEvent {
    private static final    Long    serialVersionUID = 1L;
    private                 String  appUrl;
    private                 Locale  locale;
    private                 User    user;

    public EmailEvent(User user, Locale locale, String appUrl) {
        super(user);

        this.user   = user;
        this.locale = locale;
        this.appUrl = appUrl;
    }

    public abstract String getSubject();
    public abstract String getMessage();
}
