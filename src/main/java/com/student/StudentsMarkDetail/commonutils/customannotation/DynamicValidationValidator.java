package com.student.StudentsMarkDetail.commonutils.customannotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

import java.lang.reflect.Field;
import java.util.Set;

public class DynamicValidationValidator implements ConstraintValidator<ValidParam, String> {

    private Class<?> clazz;
    private String fieldName;

    @Override
    public void initialize(ValidParam constraintAnnotation) {
        this.clazz = constraintAnnotation.clazz();
        this.fieldName = constraintAnnotation.field();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // Let @NotNull handle null checks if needed
        }

        try {
            // Get the field from the class
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);

            // Create a dummy instance of the DTO
            Object dtoInstance = clazz.getDeclaredConstructor().newInstance();
            field.set(dtoInstance, value);

            // Programmatically validate the DTO instance
            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set<ConstraintViolation<Object>> violations = validator.validateProperty(dtoInstance,fieldName);

            if (!violations.isEmpty()) {
                // Extract the first error message
                String errorMsg = violations.iterator().next().getMessage();

                // Customize the validation context with the error message
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(errorMsg).addConstraintViolation();
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error validating field: " + fieldName + " in class: " + clazz.getName(), e);
        }

        return true;
    }
}