package edu.psu.injection.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotNullNotEmptyCharacterValidator implements ConstraintValidator<NotNullNotEmptyCharacter, Character> 
{
  @Override
  public void initialize(NotNullNotEmptyCharacter validator) 
  {}

  @Override
  public boolean isValid(Character character, ConstraintValidatorContext context) 
  {
    if (character == null || character.equals(' '))
    {
      return false;
    }
    
    return true;
  }
}
