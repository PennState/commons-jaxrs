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
public enum Prefix
{
   NONE("None"),  
   MR("Mr."), 
   MS("Ms."), 
   MRS("Mrs."), 
   DR("Dr.");
   
   private String prettyString_;
   private static Map<String, Prefix> reverseLookup_ = new HashMap<String, Prefix>();
   static
   {
     reverseLookup_.put("None", NONE);
     reverseLookup_.put("Mr.", MR);
     reverseLookup_.put("Ms.", MS);
     reverseLookup_.put("Mrs.", MRS);
     reverseLookup_.put("Dr.", DR);
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
  
}
