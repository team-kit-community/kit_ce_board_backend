package com.creativedesignproject.kumoh_board_backend.Board.common;

import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;

@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ListNotEmptyValidator.class)
public @interface ListNotEmpty {
    String message() default "List must not be empty";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
