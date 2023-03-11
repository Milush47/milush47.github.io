package com.example.app.validators.plugin;

import jakarta.validation.*;
import net.bytebuddy.asm.Advice.AllArguments;
import net.bytebuddy.asm.Advice.Origin;

import java.lang.reflect.Constructor;
import java.util.Set;
import java.util.stream.Collectors;

public class RecordValidationInterceptor {
    private static final Validator VALIDATOR;

    static {
        ValidatorFactory    factory     = Validation.buildDefaultValidatorFactory();
                            VALIDATOR   = factory.getValidator();
    }

    public static <T> void validate(
            @Origin         Constructor<T>  constructor,
            @AllArguments   Object[]        args
    ) {

        Set<ConstraintViolation<T>> violations = VALIDATOR.forExecutables()
                .validateConstructorParameters(constructor, args);

        if(!violations.isEmpty()) {
            String message = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(System.lineSeparator()));

            throw new ConstraintViolationException(message, violations);
        }
    }
}
