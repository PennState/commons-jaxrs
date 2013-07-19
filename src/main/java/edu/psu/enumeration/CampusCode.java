package edu.psu.enumeration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum CampusCode 
{
  AA("Altoona campus"),
  AN("Lehigh Valley campus"),
  BD("Penn State Erie, The Behrend College"),
  BK("Berks campus"),
  BR("Beaver campus"),
  CL("Penn State Harrisburg, The Capital College"),
  DE("Brandywine campus (formerly Delaware County campus)"),
  DN("Carlisle campus"),
  DS("DuBois campus"),
  FE("Fayette campus"),
  HN("Hazleton campus"),
  HY("Milton S. Hershey Medical Center"),
  KP("Penn State Great Valley"),
  MA("Mont Alto campus"),
  MK("Greater Allegheny campus"),
  NK("New Kensington campus"),
  OZ("Abington campus"),
  PC("Pennsylvania College of Technology campus"),
  SL("Schuylkill campus"),
  SV("Shenango campus"),
  UP("University Park campus"),
  WB("Wilkes-Barre campus"),
  WD("World Campus"),
  WS("Worthington Scranton campus"),
  XC("State College Continuing Education"),
  XP("Keller Conference Center at University Park"),
  XS("Foreign Studies Program"),
  YK("York campus");
  
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
      prettyStringLookup_ = new HashMap<String, CampusCode>();
    }
    
    prettyStringLookup_.put(campusName, campusCode);
  }
  
  
}
