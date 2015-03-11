package edu.psu.injection.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotNullNotEmptyValidator implements ConstraintValidator<NotNullNotEmptyCharacter, String> 
{
  @Override
  public void initialize(NotNullNotEmptyCharacter validator) 
  {}

  @Override
  public boolean isValid(String string, ConstraintValidatorContext context) 
  {
    if (string == null || string.isEmpty())
    {
      return false;
    }
    
    return true;
  }
}
