package edu.psu.injection.validator;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = IsoCountryValidator.class)
@Target( { METHOD, FIELD, PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface  IsoCountry 
{
  String message() default "Country does not exist in the ISO standard";
	 
  Class<?>[] groups() default {};
  
  Class<? extends Payload>[] payload() default {};
}
