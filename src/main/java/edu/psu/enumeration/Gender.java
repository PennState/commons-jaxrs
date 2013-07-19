/**
 * 
 */
package edu.psu.enumeration;

import java.util.HashMap;
import java.util.Map;

public enum Gender
{
  FEMALE("Female", "F"), 
  MALE("Male", "M"), 
  OTHER("Other", "O"),
  NOT_SPECIFIED("Not Specified", "N");
  
  private String prettyString_;
  private String shortString_;
  
  private static Map<String, Gender> reverseTranslation_ = new HashMap<String, Gender>();
  private static Map<String, Gender> shortValueTranslation_ = new HashMap<String, Gender>();

  static
  {
    reverseTranslation_.put("Female", FEMALE);
    reverseTranslation_.put("Male", MALE);
    reverseTranslation_.put("Other", OTHER);
    reverseTranslation_.put("Not Specified", NOT_SPECIFIED);  
    
    shortValueTranslation_.put("F", FEMALE);
    shortValueTranslation_.put("M", MALE);
    shortValueTranslation_.put("O", OTHER);
    shortValueTranslation_.put("N", NOT_SPECIFIED);
  }
  
  Gender(String prettyString, String shortString)
  {
    prettyString_ = prettyString;
    shortString_ = shortString;
  }
  
  /**
   * Translates an English readable form of the enum to the enumerated value
   * @param prettyString
   * @return Gender or null if the prettyString equals null or if no match is found
   */
  public static Gender fromPrettyString(String prettyString)
  {
    if (prettyString != null)
    {
      if (prettyString.trim().isEmpty())
      {
        return Gender.NOT_SPECIFIED;
      }
      else
      {
        return reverseTranslation_.get(prettyString.trim());
      }
    }
    
    return null;
    
  }
  
  /**
   * Translates an single letter representation to the appropriate enum
   * This is primarily for historical purposes
   * @param shortString
   * @return Gender or null if the shortString equals null or if no match is found
   */
  @Deprecated
  public static Gender fromShortString(String shortString)
  {
    if (shortString != null)
    {
      if (shortString.trim().isEmpty())
      {
        return Gender.NOT_SPECIFIED;
      }
      else
      {
        return shortValueTranslation_.get(shortString.trim().toUpperCase());
      }
    }
    
    return null;   
  }
  
  /**
   * Returns an single character representation of gender
   * This is primarily for historical purposes
   * @return String
   */
  @Deprecated
  public String toShortString()
  {
    if (this.equals(NOT_SPECIFIED))
    {
      return " ";
    }
    
    return shortString_;
  }
  
  /**
   * Returns an English readable representation of the enumeration
   * @return String
   */
  @Override
  public String toString()
  {
    return prettyString_;
  }
}

