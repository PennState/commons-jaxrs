package edu.psu.regex;

public class RegexConstants
{
  
  public static final String CURRENCY_REGEX = "^[+-]?[0-9]{1,3}(?:,?[0-9]{3})*(?:\\.[0-9]{2})?$";
  
  /**
   * Matches Strings that contain valid PSU Ids according to the CIDR documentation
   * available at:  <a href="https://wikispaces.psu.edu/display/IdSExternal/IAM+CIDR+Functionality" />
   */
  public static final String PSU_ID_REGEX = "(?!987654321)(?!9(\\d)\\1{7})(?!9{8}\\d)^9\\d{8}$";
  
}
