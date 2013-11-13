package edu.psu.enumeration;

import java.util.HashMap;
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
  static
  {
    prettyStringTranslation_.put("White", WHITE);
    prettyStringTranslation_.put("Hispanic/Latino", HISPANIC);
    prettyStringTranslation_.put("Black/African American", BLACK);
    prettyStringTranslation_.put("Asian", ASIAN);
    prettyStringTranslation_.put("American Indian or Alaska Native", AMERICAN_INDIAN);
    prettyStringTranslation_.put("Native Hawaiian or other Pacific Islander", PACIFIC_ISLANDER);
    prettyStringTranslation_.put("Multiple Ethnicities", MULTIPLE);
    prettyStringTranslation_.put("Other", OTHER);
    prettyStringTranslation_.put("No Response", NO_RESPONSE);
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
}
