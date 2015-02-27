package edu.psu.injection.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import edu.psu.regex.RegexConstants;

public class PsuidValidator implements ConstraintValidator<PSUID, String>
{
  @Override
  public void initialize(PSUID validator)
  {
  }

  @Override
  public boolean isValid(String psuid, ConstraintValidatorContext context)
  {
    //TODO - Is this the right answer?
    if (psuid == null || psuid.isEmpty())
    {
      return true;
    }
    
    return psuid.matches(RegexConstants.PSU_ID_REGEX);
  }
}
