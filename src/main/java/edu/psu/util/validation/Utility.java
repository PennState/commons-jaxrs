package edu.psu.util.validation;

import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;

public class Utility
{
  public static String violationsToString(Set<ConstraintViolation<?>> violations)
  {
    return violations.stream()
                     .map(v -> v.getMessage())
                     .collect(Collectors.joining(", "));
  }
}
