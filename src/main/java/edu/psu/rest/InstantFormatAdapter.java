package edu.psu.rest;

import java.time.Instant;
import java.time.format.DateTimeFormatter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class InstantFormatAdapter extends XmlAdapter<String, Instant>
{
  private static DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_INSTANT;
  
  @Override
  public String marshal(Instant value) throws Exception
  {
	  if (value == null) {
		  return null;
	  }
    return FORMATTER.format(value);
  }

  @Override
  public Instant unmarshal(String s) throws Exception
  {
	  if (s == null || s.trim().isEmpty()) {
		  return null;
	  }
    return Instant.parse(s);
  }
}
