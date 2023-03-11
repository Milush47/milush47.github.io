package com.example.app.validators.plugin;

import jakarta.validation.Constraint;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.build.Plugin;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.scaffold.TypeValidation;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.SuperMethodCall;

import java.io.IOException;

import static net.bytebuddy.matcher.ElementMatchers.annotationType;
import static net.bytebuddy.matcher.ElementMatchers.hasAnnotation;

public class RecordValidationPlugin implements Plugin {
    @Override
    public DynamicType.Builder<?> apply(
            DynamicType.Builder<?>  builder,
            TypeDescription         typeDescription,
            ClassFileLocator        classFileLocator
    ) {
        try {
            builder = new ByteBuddy()
                    .with(TypeValidation.DISABLED)
                    .rebase(
                            Class.forName(typeDescription.getName())
                    );

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return builder.constructor(this::hasConstrainedParameters)
                .intercept(
                        SuperMethodCall.INSTANCE
                                .andThen(MethodDelegation.to(RecordValidationInterceptor.class)));
    }

    @Override
    public void close() throws IOException {

    }

    @Override
    public boolean matches(TypeDescription target) {
        return target.isRecord() && target.getDeclaredMethods()
                .stream()
                .anyMatch(m -> m.isConstructor() && hasConstrainedParameters(m));
    }

    private boolean hasConstrainedParameters(MethodDescription m) {
        return m.getParameters()
                .asDefined()
                .stream()
                .anyMatch(
                        p -> !p.getDeclaredAnnotations()
                        .asTypeList()
                        .filter(hasAnnotation(annotationType(Constraint.class)))
                        .isEmpty()
                );
    }
}
