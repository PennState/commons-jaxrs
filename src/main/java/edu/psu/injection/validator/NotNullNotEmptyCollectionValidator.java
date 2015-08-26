package edu.psu.injection.validator;

import java.util.Collection;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotNullNotEmptyCollectionValidator implements ConstraintValidator<NotNullNotEmptyCollection, Collection<?>> {

  @Override
  public void initialize(NotNullNotEmptyCollection newConstraintAnnotation) {

  }

  @Override
  public boolean isValid(Collection<?> newValue, ConstraintValidatorContext newContext) {
    if (newValue == null || newValue.isEmpty()) {
      return false;
    }
    return true;
  }
}
