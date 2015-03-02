package edu.psu.injection.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import edu.psu.regex.RegexConstants;

public class EmailValidator implements ConstraintValidator<Email, String>
{
  @Override
  public void initialize(Email validator)
  {
  }

  @Override
  public boolean isValid(String email, ConstraintValidatorContext context)
  {
    //TODO - Is this the right answer?
    if (email == null || email.isEmpty())
    {
      return true;
    }
    
    return email.matches(RegexConstants.EMAIL_ADDRESS_REGEX);
  }
}
