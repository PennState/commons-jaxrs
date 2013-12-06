package edu.psu.enumeration;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum Ethnicity
{
  WHITE("White"),
  HISPANIC("Hispanic/Latino"),
  BLACK("Black/African American"),
  ASIAN("Asian"),
  AMERICAN_INDIAN("American Indian or Alaska Native"),
  PACIFIC_ISLANDER("Native Hawaiian or other Pacific Islander"),
  MULTIPLE("Multiple Ethnicities"),
  OTHER("Other"),
  NO_RESPONSE("No Response");
  
  private String prettyString_;
  private static Map<String, Ethnicity> prettyStringTranslation_ = new HashMap<String, Ethnicity>();
  
  private static String ILLEGAL_ARGUMENT_MESSAGE = null;
  
  static
  {
    EnumSet<Ethnicity> set = EnumSet.allOf(Ethnicity.class);
    
    StringBuilder sb = new StringBuilder();
    Iterator<Ethnicity> iter = set.iterator();
    
    while(iter.hasNext())
    {
      Ethnicity e = iter.next();
      
      prettyStringTranslation_.put(e.prettyString_,  e);

      sb.append(e.name());
      if (iter.hasNext())
      {
        sb.append(",");
      }     
    }

    ILLEGAL_ARGUMENT_MESSAGE = "The value you passed for ETHNICITY was illegal, legal values are: " + sb.toString();
  }
  
  /**
   * Constructs an Ethnicity with an associated Pretty String
   * @param prettyString
   */
  Ethnicity(String prettyString)
  {
    prettyString_ = prettyString;
  }
  
  /**
   * Returns the enumerated equivalent of the associated pretty string
   * @param prettyString
   * @return Ethnicity or null if no match is found or a null argument is passed
   */
  public static Ethnicity fromPrettyString(final String prettyString)
  {
    if (prettyString != null)
    {
      if (prettyString.trim().isEmpty())
      {
        return prettyStringTranslation_.get("No Response");
      }
      else
      {
        return prettyStringTranslation_.get(prettyString.trim());
      }
    }

    return null;
  }
  
  @Override
  /**
   * Returns a prettified String version of the enumeration
   * @return String
   */
  public String toString()
  { 
    return prettyString_;
  }
  
  public static Ethnicity enumValue(String stringValue)
  {
    if (stringValue == null)
    {
      throw new IllegalArgumentException(ILLEGAL_ARGUMENT_MESSAGE);
    }
    
    Ethnicity e = null;
    try
    {
      e = Ethnicity.valueOf(stringValue.toUpperCase());
    }
    catch(IllegalArgumentException ex)
    {
      //Try from pretty string
      e = Ethnicity.fromPrettyString(stringValue);
      if (e == null)
      {
        throw new IllegalArgumentException(ILLEGAL_ARGUMENT_MESSAGE);
      }
    }
    
    return e;
  }
}
