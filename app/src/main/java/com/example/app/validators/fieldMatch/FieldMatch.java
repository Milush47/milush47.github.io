package com.example.app.validators.fieldMatch;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Constraint(validatedBy = FieldMatchConstraintValidator.class)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FieldMatch {
    String                      message()   default "Field don't match";
    Class<?>[]                  groups()    default {};
    Class<? extends Payload>[]  payload()   default {};

    String first();
    String second();

    @Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE, ElementType.RECORD_COMPONENT})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List
    {
        FieldMatch[] value();
    }

}
