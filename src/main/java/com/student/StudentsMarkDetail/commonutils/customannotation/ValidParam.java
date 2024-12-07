package com.student.StudentsMarkDetail.commonutils.customannotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = DynamicValidationValidator.class)
@Target({ ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidParam {

    Class<?> clazz(); // The DTO class to validate against

    String field(); // The field name in the DTO

    String message() default "Invalid value"; // Default error message

    Class<?>[] groups() default {}; // Validation groups

    Class<? extends Payload>[] payload() default {}; // Metadata payload
}