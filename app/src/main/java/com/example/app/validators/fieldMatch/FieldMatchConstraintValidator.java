package com.example.app.validators.fieldMatch;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class FieldMatchConstraintValidator implements ConstraintValidator<FieldMatch, Object> {
    private String firstFieldName;
    private String secondFieldName;
    private String message;
    @Override
    public void initialize(FieldMatch constraintAnnotation) {
        firstFieldName  = constraintAnnotation.first();
        secondFieldName = constraintAnnotation.second();
        message         = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        boolean valid = true;

        Object firstObject  = new BeanWrapperImpl(value).getPropertyValue(firstFieldName);
        Object secondObject = new BeanWrapperImpl(value).getPropertyValue(secondFieldName);

        valid = firstObject == null && secondObject == null ||
                firstObject != null && firstObject.equals(secondObject);

        return valid;
    }
}
