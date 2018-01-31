package edu.psu.swe.commons.jaxrs.adapters;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class ZonedDateTimeAdapter extends XmlAdapter<String, ZonedDateTime> {
  
  private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_DATE_TIME;
  private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ISO_INSTANT;

  @Override
  public ZonedDateTime unmarshal(String v) throws Exception {
    if (v == null) {
      return null;
    }

    ZonedDateTime zonedDateTime = null;
    try {
      zonedDateTime = ZonedDateTime.parse(v, FORMATTER);
    } catch (DateTimeException e) {
      // couldn't parse to a ZoneDateTime, try LocalDateTime
      LocalDateTime dt = LocalDateTime.parse(v, FORMATTER);

      // default to EST
      zonedDateTime = dt.atZone(ZoneId.systemDefault());
    }

    return zonedDateTime;
  }

  @Override
  public String marshal(ZonedDateTime v) throws Exception {
    if (v == null) {
      return null;
    }
    return OUTPUT_FORMATTER.format(v);
  }
}
