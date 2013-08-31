package edu.psu.enumeration;

import java.util.HashMap;
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
  private static Map<String, State> shortVersionLookup_ = null;
  
  static
  {
    reverseLookup_ = new HashMap<String, State>();
    shortVersionLookup_ = new HashMap<String, State>();
    
    reverseLookup_.put("Not Specified", NOT_SPECIFIED);
    shortVersionLookup_.put("NS", NOT_SPECIFIED);
    
    reverseLookup_.put("AE - Armed Forces Europe", AE);
    shortVersionLookup_.put("AE", AE);
    reverseLookup_.put("AP - Armed Forces Pacific", AP);
    shortVersionLookup_.put("AP", AP);
    reverseLookup_.put("AA - Armed Forces Americas", AA);
    shortVersionLookup_.put("AA", AA);
    reverseLookup_.put("American Samoa", AMERICAN_SAMOA);
    shortVersionLookup_.put("AS", AMERICAN_SAMOA);
    reverseLookup_.put("Alabama", ALABAMA);
    shortVersionLookup_.put("AL", ALABAMA);
    reverseLookup_.put("Alaska", ALASKA);
    shortVersionLookup_.put("AK", ALASKA);
    reverseLookup_.put("Arizona", ARIZONA);
    shortVersionLookup_.put("AZ", ARIZONA);
    reverseLookup_.put("Arkansas", ARKANSAS);
    shortVersionLookup_.put("AR", ARKANSAS);
    reverseLookup_.put("California", CALIFORNIA);
    shortVersionLookup_.put("CA", CALIFORNIA);
    reverseLookup_.put("Colorado", COLORADO);
    shortVersionLookup_.put("CO", COLORADO);
    reverseLookup_.put("Connecticut", CONNECTICUT);
    shortVersionLookup_.put("CT", CONNECTICUT);
    reverseLookup_.put("Delaware", DELAWARE);
    shortVersionLookup_.put("DE", DELAWARE);
    reverseLookup_.put("District of Columbia", DISTRICT_OF_COLUMBIA);
    shortVersionLookup_.put("DC", DISTRICT_OF_COLUMBIA);
    reverseLookup_.put("Florida", FLORIDA);
    shortVersionLookup_.put("FL", FLORIDA);
    reverseLookup_.put("Georgia", GEORGIA);
    shortVersionLookup_.put("GA", GEORGIA);
    reverseLookup_.put("Guam", GUAM);
    shortVersionLookup_.put("GU", GUAM);
    reverseLookup_.put("Hawaii", HAWAII);
    shortVersionLookup_.put("HI", HAWAII);
    reverseLookup_.put("Idaho", IDAHO);
    shortVersionLookup_.put("ID", IDAHO);
    reverseLookup_.put("Illinois", ILLINOIS);
    shortVersionLookup_.put("IL", ILLINOIS);
    reverseLookup_.put("Indiana", INDIANA);
    shortVersionLookup_.put("IN", INDIANA);
    reverseLookup_.put("Iowa", IOWA);
    shortVersionLookup_.put("IA", IOWA);
    reverseLookup_.put("Kansas", KANSAS);
    shortVersionLookup_.put("KS", KANSAS);
    reverseLookup_.put("Kentucky", KENTUCKY);
    shortVersionLookup_.put("KY", KENTUCKY);
    reverseLookup_.put("Louisiana", LOUISIANA);
    shortVersionLookup_.put("LA", LOUISIANA);
    reverseLookup_.put("Maine", MAINE);
    shortVersionLookup_.put("ME", MAINE);
    reverseLookup_.put("Maryland", MARYLAND);
    shortVersionLookup_.put("MD", MARYLAND);
    reverseLookup_.put("Massachusetts", MASSACHUSETTS);
    shortVersionLookup_.put("MA", MASSACHUSETTS);
    reverseLookup_.put("Michigan", MICHIGAN);
    shortVersionLookup_.put("MI", MICHIGAN);
    reverseLookup_.put("Minnesota", MINNESOTA);
    shortVersionLookup_.put("MN", MINNESOTA);
    reverseLookup_.put("Mississippi", MISSISSIPPI);
    shortVersionLookup_.put("MS", MISSISSIPPI);
    reverseLookup_.put("Missouri", MISSOURI);
    shortVersionLookup_.put("MO", MISSOURI);
    reverseLookup_.put("Montana", MONTANA);
    shortVersionLookup_.put("MT", MONTANA);
    reverseLookup_.put("Nebraska", NEBRASKA);
    shortVersionLookup_.put("NE", NEBRASKA);
    reverseLookup_.put("Nevada", NEVADA);
    shortVersionLookup_.put("NV", NEVADA);
    reverseLookup_.put("New Hampshire", NEW_HAMPSHIRE);
    shortVersionLookup_.put("NH", NEW_HAMPSHIRE);
    reverseLookup_.put("New Jersey", NEW_JERSEY);
    shortVersionLookup_.put("NJ", NEW_JERSEY);
    reverseLookup_.put("New Mexico", NEW_MEXICO);
    shortVersionLookup_.put("NM", NEW_MEXICO);
    reverseLookup_.put("New York", NEW_YORK);
    shortVersionLookup_.put("NY", NEW_YORK);
    reverseLookup_.put("North Carolina", NORTH_CAROLINA);
    shortVersionLookup_.put("NC", NORTH_CAROLINA);
    reverseLookup_.put("North Dakota", NORTH_DAKOTA);
    shortVersionLookup_.put("ND", NORTH_DAKOTA);
    reverseLookup_.put("Northern Mariana Islands", NORTHERN_MARIANA_ISLANDS);
    shortVersionLookup_.put("MP", NORTHERN_MARIANA_ISLANDS);
    reverseLookup_.put("Ohio", OHIO);
    shortVersionLookup_.put("OH", OHIO);
    reverseLookup_.put("Oklahoma", OKLAHOMA);
    shortVersionLookup_.put("OK", OKLAHOMA);
    reverseLookup_.put("Oregon", OREGON);
    shortVersionLookup_.put("OR", OREGON);
    reverseLookup_.put("Pennsylvania", PENNSYLVANIA);
    shortVersionLookup_.put("PA", PENNSYLVANIA);
    reverseLookup_.put("Puerto Rico", PUERTO_RICO);
    shortVersionLookup_.put("PR", PUERTO_RICO);
    reverseLookup_.put("Rhode Island", RHODE_ISLAND);
    shortVersionLookup_.put("RI", RHODE_ISLAND);
    reverseLookup_.put("South Carolina", SOUTH_CAROLINA);
    shortVersionLookup_.put("SC", SOUTH_CAROLINA);
    reverseLookup_.put("South Dakota", SOUTH_DAKOTA);
    shortVersionLookup_.put("SD", SOUTH_DAKOTA);
    reverseLookup_.put("Tennessee", TENNESSEE);
    shortVersionLookup_.put("TN", TENNESSEE);
    reverseLookup_.put("Texas", TEXAS);
    shortVersionLookup_.put("TX", TEXAS);
    reverseLookup_.put("Utah", UTAH);
    shortVersionLookup_.put("UT", UTAH);
    reverseLookup_.put("Vermont", VERMONT);
    shortVersionLookup_.put("VT", VERMONT);
    reverseLookup_.put("Virginia", VIRGINIA);
    shortVersionLookup_.put("VA", VIRGINIA);
    reverseLookup_.put("Virgin Islands", VIRGIN_ISLANDS);
    shortVersionLookup_.put("VI", VIRGIN_ISLANDS);
    reverseLookup_.put("Washington", WASHINGTON);
    shortVersionLookup_.put("WA", WASHINGTON);
    reverseLookup_.put("West Virginia", WEST_VIRGINIA);
    shortVersionLookup_.put("WV", WEST_VIRGINIA);
    reverseLookup_.put("Wisconsin", WISCONSIN);
    shortVersionLookup_.put("WI", WISCONSIN);
    reverseLookup_.put("Wyoming", WYOMING);
    shortVersionLookup_.put("WY", WYOMING);
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
			return shortVersionLookup_.get(abbreviation.toUpperCase().trim());
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
  
//  private static synchronized void addLookupValues(State state, String shortVersion, String prettyString)
//  {
//    System.out.println("############ IN ADD LOOKUP VALUES");
//    if (reverseLookup_ == null)
//    {
//      reverseLookup_ = new HashMap<String, State>();
//    }
//    
//    if (shortVersionLookup_ == null)
//    {
//      System.out.println("########### INITIALIZING SHORT VERSION LOOKUP");
//      shortVersionLookup_ = new HashMap<String, State>();
//    }
//    
//    reverseLookup_.put(prettyString, state);
//    System.out.println("Short version putting " + shortVersion + " " + state);
//    shortVersionLookup_.put(shortVersion, state);
//  }
}
