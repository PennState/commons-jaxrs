package edu.psu.rest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class LocalDateTimeParamWrapper {

  private static DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

  private LocalDateTime localDateTime;

  public LocalDateTimeParamWrapper(String s) {
    if (s != null) {
      localDateTime = LocalDateTime.parse(s);
    }
  }

  public LocalDateTimeParamWrapper(LocalDateTime localDateTime) {
    this.localDateTime = localDateTime;
  }
  
  @Override
  public String toString() {
    return localDateTime != null ? FORMATTER.format(localDateTime) : null;
  }

  public Optional<LocalDateTime> getLocalDateTime() {
    return Optional.ofNullable(localDateTime);
  }
}
