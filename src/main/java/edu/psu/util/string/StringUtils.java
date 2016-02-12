package edu.psu.util.string;

public class StringUtils
{
  private StringUtils()
  {}
  
  //The following two methods are base on code found here - http://www.rgagnon.com/javadetails/java-0448.html
  public static String padRight(String s, int n) 
  {
    return String.format("%1$-" + n + "s", s);
  }

  public static String padLeft(String s, int n) 
  {
    return String.format("%1$" + n + "s", s);
  }
}
