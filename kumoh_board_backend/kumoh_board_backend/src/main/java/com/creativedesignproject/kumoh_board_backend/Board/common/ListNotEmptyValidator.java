package com.creativedesignproject.kumoh_board_backend.Board.common;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class ListNotEmptyValidator implements ConstraintValidator<ListNotEmpty, List<?>>{
    @Override
    public boolean isValid(List<?> value, ConstraintValidatorContext context) {
        return value != null && !value.isEmpty();
    }
}
