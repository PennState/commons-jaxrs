/**
 * 
 */
package edu.psu.enumeration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ses44
 *
 */
public enum Suffix
{
  NONE(""),
  JR("Jr."),
  SR("Sr."),
  II("II"),
  III("III"),
  IV("IV"),
  V("V"),
  VI("VI"),
  VII("VII"),
  VIII("VIII"),
  IX("IX"),
  X("X");
  
  private String prettyString_;
  private static Map<String, Suffix> reverseTranslation_ = new HashMap<String, Suffix>();
  
  static
  {
    reverseTranslation_.put("", NONE);
    reverseTranslation_.put("Jr.", JR);
    reverseTranslation_.put("Sr.", SR);    
    reverseTranslation_.put("II", II);
    reverseTranslation_.put("III", III);
    reverseTranslation_.put("IV", IV);
    reverseTranslation_.put("V", V);
    reverseTranslation_.put("VI", VI);
    reverseTranslation_.put("VII", VII);
    reverseTranslation_.put("VIII", VIII);
    reverseTranslation_.put("IX", IX);
    reverseTranslation_.put("X", X);
  }
  
  Suffix(String prettyString)
  {
    prettyString_ = prettyString;
  }
  
  /**
   * Translates from the English readable string to the appropriate Enumerted value
   * @param prettyString
   * @return Prefix or null if either not found or a null argument is received
   */
  public static Suffix fromPrettyString(String prettyString)
  {
    if (prettyString != null)
    { 
      if (prettyString.trim().isEmpty())
      {
        return Suffix.NONE;
      }
      
      return reverseTranslation_.get(prettyString.trim());
    }
    
    return null;
  }
  
  @Override
  /**
   * Returns an English readable representation of the enumeration or an empty string Prefix == None
   * @return String
   */
  public String toString()
  {
    if (this.equals(NONE))
    {
      return " ";
    }
    
    return prettyString_;
  }
}
