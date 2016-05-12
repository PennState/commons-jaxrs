package edu.psu.rest;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class InstantParamWrapper {

  private static DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_INSTANT;

  private Instant instant;

  public InstantParamWrapper(String s) {
    if (s != null) {
      instant = Instant.parse(s);
    }
  }
  
  public InstantParamWrapper(Instant instant) {
    this.instant = instant;
  }

  @Override
  public String toString() {
    return getInstant() != null ? FORMATTER.format(instant) : null;
  }

  public Optional<Instant> getInstant() {
    return Optional.ofNullable(instant);
  }

}
