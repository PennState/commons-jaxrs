/**
 * 
 */
package edu.psu.enumeration;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import static edu.psu.enumeration.Constants.BARE_QUESTION_LOOKUP_REGEX;

/**
 * @author ses44
 *
 */
public enum Prefix
{
   NONE("None"),  
   MR("Mr."), 
   MS("Ms."), 
   MRS("Mrs."), 
   DR("Dr.");
   
   private String prettyString_;
   private static Map<String, Prefix> reverseLookup_ = new HashMap<String, Prefix>();
   private static String ILLEGAL_ARGUMENT_MESSAGE = null;
   
   static
   {
     EnumSet<Prefix> set = EnumSet.allOf(Prefix.class);
     
     StringBuilder sb = new StringBuilder();
     Iterator<Prefix> iter = set.iterator();
     
     while(iter.hasNext())
     {
       Prefix s = iter.next();

       reverseLookup_.put(s.prettyString_,  s);
       reverseLookup_.put(s.prettyString_.replaceAll(BARE_QUESTION_LOOKUP_REGEX, "").toLowerCase(), s);
       reverseLookup_.put(s.name().replaceAll(BARE_QUESTION_LOOKUP_REGEX, "").toLowerCase(), s);

       sb.append(s.name());
       if (iter.hasNext())
       {
         sb.append(",");
       }            
     }
      
     ILLEGAL_ARGUMENT_MESSAGE = "The value you passed for PREFIX was illegal, legal values are: " + sb.toString();
   }
   
   /**
    * This enum identifies the allowable prefixes
    */
   Prefix(String prettyString)
   {
     prettyString_ = prettyString;
   }
   
   /**
    * Does a reverse lookup from the pretty string to the Prefix enum
    * @param prettyString
    * @return Prefix or null if give either a null or invalid argument
    */
   public static Prefix fromPrettyString(String prettyString)
   {
     if (prettyString != null)
     {
       if (prettyString.trim().isEmpty())
       {
         return Prefix.NONE;
       }
       
       return reverseLookup_.get(prettyString.trim());
     }
     
     return null;
   }
   
   /**
    * Returns an English readable version of the enumeration
    * @return String
    */
   @Override
   public String toString()
   {
     if (this.equals(NONE))
     {
       return " ";
     }
     
     return prettyString_;
   }
  
   public static Prefix enumValue(String stringValue)
   {
     if (stringValue == null)
     {
       throw new IllegalArgumentException(ILLEGAL_ARGUMENT_MESSAGE);
     }
     
     Prefix s = null;
     String trimmedValue = stringValue.trim();
     try
     {
       s = Prefix.valueOf(trimmedValue.toUpperCase());
     }
     catch(IllegalArgumentException e)
     {
       //Try from pretty string
       s = Prefix.fromPrettyString(trimmedValue);
       if (s == null)
       {
         throw new IllegalArgumentException(ILLEGAL_ARGUMENT_MESSAGE);
       }
     }
     
     return s;
   }
}
