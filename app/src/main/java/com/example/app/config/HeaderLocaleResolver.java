package com.example.app.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.List;
import java.util.Locale;

public class HeaderLocaleResolver extends AcceptHeaderLocaleResolver {

    private static final String HEADER_NAME = "Accept-Language";

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String headerValue = request.getHeader(HEADER_NAME);
        if (headerValue == null || headerValue.isBlank()) {
            return super.resolveLocale(request);
        } else {
            List<Locale.LanguageRange> ranges = Locale.LanguageRange.parse(headerValue);
            Locale locale = Locale.lookup(ranges, getSupportedLocales());
            if (locale == null) {
                locale = super.resolveLocale(request);
            }
            return locale;
        }
    }
}
