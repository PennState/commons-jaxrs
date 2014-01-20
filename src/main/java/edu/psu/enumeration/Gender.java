/**
 * 
 */
package edu.psu.enumeration;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum Gender
{
  FEMALE("Female", "F"), 
  MALE("Male", "M"), 
  OTHER("Other", "O"),
  NOT_SPECIFIED("Not Specified", "N");
  
  private String prettyString_;
  private String shortString_;
  
  private static String ILLEGAL_ARGUMENT_MESSAGE = null;
  
  private static Map<String, Gender> reverseTranslation_ = new HashMap<String, Gender>();
  private static Map<String, Gender> shortValueTranslation_ = new HashMap<String, Gender>();

  static
  {
    EnumSet<Gender> set = EnumSet.allOf(Gender.class);
    
    StringBuilder sb = new StringBuilder();
    Iterator<Gender> iter = set.iterator();
    
    while(iter.hasNext())
    {
      Gender g = iter.next();
      
      reverseTranslation_.put(g.prettyString_,  g);
      shortValueTranslation_.put(g.shortString_,  g);

      sb.append(g.name());
      sb.append(",");
      sb.append(g.shortString_);
      if (iter.hasNext())
      {
        sb.append(", ");
      }            
    }
     
    ILLEGAL_ARGUMENT_MESSAGE = "The value you passed for GENDER was illegal, legal values are: " + sb.toString();
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
  
  public static Gender enumValue(String stringValue)
  {
    if (stringValue == null)
    {
      throw new IllegalArgumentException(ILLEGAL_ARGUMENT_MESSAGE);
    }
    
    Gender g = null;
    String trimmedValue = stringValue.trim();
    try
    {
      g = Gender.valueOf(trimmedValue.toUpperCase());
    }
    catch(IllegalArgumentException e)
    {
      //Try from pretty string
      g = Gender.fromPrettyString(trimmedValue);
      if (g == null)
      {
        g = Gender.fromShortString(trimmedValue);
        
        if (g == null)
        {
          throw new IllegalArgumentException(ILLEGAL_ARGUMENT_MESSAGE);
        }
      }
    }
    
    return g;
  }
}

