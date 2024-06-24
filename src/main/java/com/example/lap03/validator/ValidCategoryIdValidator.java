package com.example.lap03.validator;

import com.example.lap03.validator.annotation.ValidCategoryId;
import com.example.lap03.entity.Category;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidCategoryIdValidator implements ConstraintValidator<ValidCategoryId,Category>{
    @Override
    public boolean isValid(Category category, ConstraintValidatorContext context) {
        return category !=null && category.getId() !=null;
    }
}
