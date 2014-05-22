package edu.psu.regex;

import static org.junit.Assert.*;

import org.junit.Test;

public class RegexConstantsTest
{
  public static final String GOOD_CURRENCY_VALUE_WITH_CENTS = "1233.12";
  public static final String GOOD_CURRENCY_VALUE_NO_CENTS = "1233";
  public static final String GOOD_CURRENCY_VALUE_COMMA_SEPARATED = "1,233.13";
  
  @Test
  public void testGoodCurrencyWithCents()
  {
    if (!GOOD_CURRENCY_VALUE_WITH_CENTS.matches(RegexConstants.CURRENCY_REGEX))
    {
      fail(GOOD_CURRENCY_VALUE_WITH_CENTS + " failed match");
    }
  }

  @Test
  public void testGoodCurrencyWithNoCents()
  {
    if (!GOOD_CURRENCY_VALUE_NO_CENTS.matches(RegexConstants.CURRENCY_REGEX))
    {
      fail(GOOD_CURRENCY_VALUE_NO_CENTS + " failed match");
    }
  }
  
  @Test
  public void testGoodCurrency()
  {
    if (!GOOD_CURRENCY_VALUE_COMMA_SEPARATED.matches(RegexConstants.CURRENCY_REGEX))
    {
      fail(GOOD_CURRENCY_VALUE_COMMA_SEPARATED + " failed match");
    }
  }
}
