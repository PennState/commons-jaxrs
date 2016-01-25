package edu.psu.regex;

import static org.junit.Assert.*;

import org.junit.Test;

public class RegexConstantsTest
{
  public static final String GOOD_CURRENCY_VALUE_WITH_CENTS = "1233.12";
  public static final String GOOD_CURRENCY_VALUE_NO_CENTS = "1233";
  public static final String GOOD_CURRENCY_VALUE_COMMA_SEPARATED = "1,233.13";
  
  public static final String BAD_CURRENCY_PERIOD_ONLY = ".";
  public static final String BAD_CURRENCY_MULTIPLE_PERIODS = "1234.22.22";

  @Test
  public void testGoodCurrencyWithCents()
  {
    if (!GOOD_CURRENCY_VALUE_WITH_CENTS.matches(RegexConstants.US_CURRENCY_REGEX))
    {
      fail(GOOD_CURRENCY_VALUE_WITH_CENTS + " failed match");
    }
  }

  @Test
  public void testGoodCurrencyWithNoCents()
  {
    if (!GOOD_CURRENCY_VALUE_NO_CENTS.matches(RegexConstants.US_CURRENCY_REGEX))
    {
      fail(GOOD_CURRENCY_VALUE_NO_CENTS + " failed match");
    }
  }
  
  @Test
  public void testGoodCurrency()
  {
    if (!GOOD_CURRENCY_VALUE_COMMA_SEPARATED.matches(RegexConstants.US_CURRENCY_REGEX))
    {
      fail(GOOD_CURRENCY_VALUE_COMMA_SEPARATED + " failed match");
    }
  }
  
  @Test
  public void testBadCurrencyPeriodOnly()
  {
    if (BAD_CURRENCY_PERIOD_ONLY.matches(RegexConstants.US_CURRENCY_REGEX))
    {
      fail(BAD_CURRENCY_PERIOD_ONLY + " should not match");
    }
  }
  
  @Test
  public void testBadCurrencyEmptyString()
  {
    if ("".matches(RegexConstants.US_CURRENCY_REGEX))
    {
      fail("Empty string should not match");
    }
  }
  
  @Test
  public void testBadMultiplePeriods()
  {
    if (BAD_CURRENCY_MULTIPLE_PERIODS.matches(RegexConstants.US_CURRENCY_REGEX))
    {
      fail(BAD_CURRENCY_MULTIPLE_PERIODS + " should not match");
    }
  }
}
