package edu.psu.injection.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsoCountryValidator implements ConstraintValidator<IsoCountry, String>
{
  @Override
  public void initialize(IsoCountry validator)
  {
  }

  @Override
  public boolean isValid(String country, ConstraintValidatorContext context)
  {
    try 
    {
      edu.psu.enumeration.Country c = edu.psu.enumeration.Country.enumValue(country);
    }
    catch (Exception e) 
    {
      //I know, raw exceptions are bad, but here we don't care.  Any inablity to convert is an error.
      return false;
    }

    return true;
  }
}
