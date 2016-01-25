package edu.psu.rest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class LocalDateParamWrapper {

  private static DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

  private LocalDate localDate;

  public LocalDateParamWrapper(String s) {
    if (s != null) {
      localDate = LocalDate.parse(s);
    }
  }

  public LocalDateParamWrapper(LocalDate localDate) {
    this.localDate = localDate;
  }
  
  @Override
  public String toString() {
    return localDate != null ? FORMATTER.format(localDate) : null;
  }

  public Optional<LocalDate> getLocalDate() {
    return Optional.ofNullable(localDate);
  }


}
