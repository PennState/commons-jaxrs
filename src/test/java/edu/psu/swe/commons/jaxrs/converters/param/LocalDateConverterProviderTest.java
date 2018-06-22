package edu.psu.swe.commons.jaxrs.converters.param;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import javax.ws.rs.ext.ParamConverter;

import org.junit.Assert;
import org.junit.Test;

import edu.psu.swe.commons.jaxrs.converters.param.LocalDateConverterProvider;


public class LocalDateConverterProviderTest {
  
  private static LocalDateConverterProvider provider  = new LocalDateConverterProvider();
  private static ParamConverter<LocalDate> converter = provider.getConverter(LocalDate.class, null, null);
  
  private static final String EXPECTED_DATE_STR = "2018-06-22";
  private static final LocalDate EXPECTED_DATE = LocalDate.of(2018, 6, 22);
  private static final String NON_DATE_STR = "hello world";
  
  @Test
  public void testFromString() throws Exception {
    LocalDate date = converter.fromString(EXPECTED_DATE_STR);
    Assert.assertEquals(EXPECTED_DATE, date);
    
    boolean caughtExeption = false;
    try {
      converter.fromString(NON_DATE_STR);
    } catch (DateTimeParseException e) {
      caughtExeption = true;
    }
    Assert.assertTrue("Did not catch the expected parsing exception", caughtExeption);
  }
  
  @Test
  public void testToString() throws Exception {
    String dateStr = converter.toString(EXPECTED_DATE);
    Assert.assertEquals(EXPECTED_DATE_STR, dateStr);
  }

}
