package edu.psu.enumeration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public enum CampusCode 
{
	// REFERENCES:
	// 1. Legacy ISIS codes - http://dus.psu.edu/legacy/codes.htm  
	// 2. OPP building lists from FIS
	// 3. OHR job campus/location list - https://app2.ohr.psu.edu/Jobs/External/EVMS2_External/currentap1.cfm
	
  AA("Altoona campus"),													// 1
  AN("Lehigh Valley campus"),											// 1
  BD("Penn State Erie, The Behrend College"),							// 1
  BK("Berks campus"),													// 1
  BR("Beaver campus"),													// 1
  CL("Penn State Harrisburg, The Capital College"),						// 1
  DE("Brandywine campus (formerly Delaware County campus)"),			// 1
  DN("Carlisle campus"),												// 1
  DS("DuBois campus"),													// 1 // See also DB
  EC("SouthEast Agricultural Research & Extension Center"),				// 2 // in Landisville, PA
  FE("Fayette campus"),													// 1
  FR("Fruit Research Extension Office"),								// 2
  GV("Great Valley"),													// 2
  HN("Hazleton campus"),												// 1
  HY("Milton S. Hershey Medical Center"),								// 1 // See also CM (College of Medicine)
  KP("Penn State Great Valley"),										// 1 // Outdated?  See GV
  MA("Mont Alto campus"),												// 1
  MK("Greater Allegheny campus"),										// 1
  NK("New Kensington campus"),											// 1
  OC("Off Campus Sites"),												// 2 // Relative to UP -- transmitters, observatories, etc.
  OZ("Abington campus"),												// 1
  PC("Pennsylvania College of Technology campus"),						// 1
  RS("Rock Springs"),													// 2
  SE("SouthEast Agricultural Research & Extension Center"),   // 2 // in Landisville, PA
  SL("Schuylkill campus"),												// 1
  ST("Shaver's Creek Environmental Center"),    //2
  SV("Shenango campus"),												// 1
  UP("University Park campus"),											// 1
  WB("Wilkes-Barre campus"),											// 1
  WD("World Campus"),													// 1
  WS("Worthington Scranton campus"),									// 1
  XC("State College Continuing Education"),								// 1
  XP("Keller Conference Center at University Park"),					// 1
  XS("Foreign Studies Program"),										// 1
  YK("York campus");													// 1

/*
  CM("College of Medicine at the Milton S. Hershey Medical Center")		// 3 // Non-standard code?
  DB("Penn State DuBois"),												// 3 // Non-standard code?
  SK("Penn State Schuylkill"),											// 3 // Non-standard code?
  AG("Penn State Extension"),											// 3
  LT("Penn State Learning Center at Lewistown"),						// 3
  WA("Navigation Center Penn State (Warminster, PA)"),					// 3
*/
  
  private List<String> alternateCodes_ = new ArrayList<String>();
  private String campusName_;

  private static Map<String, CampusCode> prettyStringLookup_;

  CampusCode(String campusName, String ... alternateCodes)
  {
    if (alternateCodes.length > 0)
    {
      alternateCodes_ = Arrays.asList(alternateCodes);
    }

    campusName_ = campusName;

    addReverseLookupInformation(this, campusName_);
  }
  
  public boolean equivalent(CampusCode other)
  {
    return equivalent(other.name());
  }
  
  public boolean equivalent(String other)
  {
    return alternateCodes_.contains(other);
  }
  
  private static synchronized void addReverseLookupInformation(CampusCode campusCode, String campusName)
  {
    if (prettyStringLookup_ == null)
    {
      prettyStringLookup_ = new LinkedHashMap<String, CampusCode>();
    }
    
    prettyStringLookup_.put(campusName, campusCode);
  }
  
  public static Map<String, CampusCode> getPrettyStringLookup() {
	return prettyStringLookup_;
  }
  
}
