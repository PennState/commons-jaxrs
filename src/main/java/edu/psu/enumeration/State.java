package edu.psu.enumeration;

import static edu.psu.enumeration.Constants.BARE_QUESTION_LOOKUP_REGEX;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum State 
{
  NOT_SPECIFIED("NS", "Not Specified"),
  AE("AE", "AE - Armed Forces Europe"),
  AP("AP", "AP - Armed Forces Pacific"),
  AA("AA", "AA - Armed Forces Americas"),
  AMERICAN_SAMOA("AS", "American Samoa"),
  ALABAMA("AL", "Alabama"),
  ALASKA("AK", "Alaska"),
  ARIZONA("AZ", "Arizona"),
  ARKANSAS("AR", "Arkansas"),
  CALIFORNIA("CA", "California"),
  COLORADO("CO", "Colorado"),
  CONNECTICUT("CT", "Connecticut"),
  DELAWARE("DE", "Delaware"),
  DISTRICT_OF_COLUMBIA("DC", "District of Columbia"),
  FLORIDA("FL", "Florida"),
  GEORGIA("GA", "Georgia"),
  GUAM("GU", "Guam"),
  HAWAII("HI", "Hawaii"),
  IDAHO("ID", "Idaho"),
  ILLINOIS("IL", "Illinois"),
  INDIANA("IN", "Indiana"),
  IOWA("IA", "Iowa"),
  KANSAS("KS", "Kansas"),
  KENTUCKY("KY", "Kentucky"),
  LOUISIANA("LA", "Louisiana"),
  MAINE("ME", "Maine"),
  MARYLAND("MD", "Maryland"),
  MASSACHUSETTS("MA", "Massachusetts"),
  MICHIGAN("MI", "Michigan"),
  MINNESOTA("MN", "Minnesota"),   
  MISSISSIPPI("MS", "Mississippi"),
  MISSOURI("MO", "Missouri"),
  MONTANA("MT", "Montana"),
  NEBRASKA("NE", "Nebraska"),
  NEVADA("NV", "Nevada"),
  NEW_HAMPSHIRE("NH", "New Hampshire"),
  NEW_JERSEY("NJ", "New Jersey"),
  NEW_MEXICO("NM", "New Mexico"),
  NEW_YORK("NY", "New York"),
  NORTH_CAROLINA("NC", "North Carolina"),
  NORTH_DAKOTA("ND", "North Dakota"),
  NORTHERN_MARIANA_ISLANDS("MP", "Northern Mariana Islands"),
  OHIO("OH", "Ohio"),
  OKLAHOMA("OK", "Oklahoma"),
  OREGON("OR", "Oregon"),
  PENNSYLVANIA("PA", "Pennsylvania"),
  PUERTO_RICO("PR", "Puerto Rico"),
  RHODE_ISLAND("RI", "Rhode Island"),
  SOUTH_CAROLINA("SC", "South Carolina"),
  SOUTH_DAKOTA("SD", "South Dakota"),
  TENNESSEE("TN", "Tennessee"),
  TEXAS("TX", "Texas"),
  UTAH("UT", "Utah"),
  VERMONT("VT", "Vermont"),
  VIRGINIA("VA", "Virginia"),
  VIRGIN_ISLANDS("VI", "Virgin Islands"),
  WASHINGTON("WA", "Washington"),
  WEST_VIRGINIA("WV", "West Virginia"),
  WISCONSIN("WI", "Wisconsin"),
  WYOMING("WY", "Wyoming");   
  
  private String prettyString_;
  private String shortVersion_;
  
  private static Map<String, State> reverseLookup_ = null;
  private static String ILLEGAL_ARGUMENT_MESSAGE = null;
  
  static
  {
    reverseLookup_ = new HashMap<String, State>();
    
    EnumSet<State> set = EnumSet.allOf(State.class);
    
    StringBuilder sb = new StringBuilder();
    Iterator<State> iter = set.iterator();
    while(iter.hasNext())
    {
      State s = iter.next();
      reverseLookup_.put(s.prettyString_, s);
      reverseLookup_.put(s.shortVersion_,  s);
      reverseLookup_.put(s.shortVersion_.toLowerCase(),  s);
      reverseLookup_.put(s.prettyString_.replaceAll(BARE_QUESTION_LOOKUP_REGEX, "").toLowerCase(), s);
      reverseLookup_.put(s.name().toLowerCase(),  s);
      
      sb.append(s.name());
      sb.append(",");
      sb.append(s.shortVersion_);
      if (iter.hasNext())
      {
        sb.append(",");
      }           
    }
     
    ILLEGAL_ARGUMENT_MESSAGE = "The value you passed for STATE was illegal, legal values are: " + sb.toString();
  }

  
  /**
   * Constructor taking a displayable translation of the enumerated value
   * @param prettyString
   */
  State(String shortVersion, String prettyString)
  {
    prettyString_ = prettyString;
    shortVersion_ = shortVersion;
    //System.out.println("############ Calling addLookupValues");
    //State.addLookupValues(this, shortVersion_, prettyString_);
  }
  
  /**
   * Returns the USPS abbreviated version of the state name
   * @return String
   */
  public String asAbbreviation()
  {
    if (this.equals(NOT_SPECIFIED))
    {
      return " ";
    }
    
    return shortVersion_;
  }
  
  /**
   * Attempts to return the state enumeration from the abbreviated name
   * @param abbreviation
   * @return the State abbreviation or null if invalid
   */
  public static State fromAbbreviation(String abbreviation)
  {
	  
    if (abbreviation != null) 
    {
		if (abbreviation.trim().isEmpty()) 
		{
			return State.NOT_SPECIFIED;
		} 
		else 
		{
			return reverseLookup_.get(abbreviation.trim());
		}
	}
    
    return null;
  }
   
  /**
   * Translates the displayable version of the enumerated type to an equivalent enum
   * @param prettyString or null if either not found or prettyString is null
   * @return
   */
  public static State fromPrettyString(String prettyString)
  {
    if (prettyString == null || prettyString.isEmpty())
    {
      return null;
    }
    
    return reverseLookup_.get(prettyString.trim());  
  }
  
  @Override
  public String toString()
  {
    return prettyString_;
  }
  
  public static State enumValue(String stringValue)
  {
    if (stringValue == null)
    {
      throw new IllegalArgumentException(ILLEGAL_ARGUMENT_MESSAGE);
    }
    
    State s = null;
    String trimmedValue = stringValue.trim();
    try
    {
      s = State.valueOf(trimmedValue.toUpperCase());
    }
    catch(IllegalArgumentException e)
    {
      //Try from pretty string
      s = reverseLookup_.get(trimmedValue.toLowerCase());
      if (s == null)
      {
        s = reverseLookup_.get(trimmedValue.replaceAll(BARE_QUESTION_LOOKUP_REGEX, "").toLowerCase());
        
        if (s == null)
        {
          throw new IllegalArgumentException(ILLEGAL_ARGUMENT_MESSAGE);
        }
      }
    }
    
    return s;
  }
}
