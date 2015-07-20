package edu.psu.util.validation;

import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;

public class Utility
{
  public static <T>  String violationsToString(Set<ConstraintViolation<T>> violations)
  {
    return violations.stream()
                     .map(v -> v.getMessage())
                     .collect(Collectors.joining(", "));
  }
}
