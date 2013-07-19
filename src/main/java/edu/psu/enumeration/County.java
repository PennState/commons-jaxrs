package edu.psu.enumeration;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

@XmlEnum(String.class)
public enum County 
{
  @XmlEnumValue("Cameron County") CAMERON("Cameron County"),  
  @XmlEnumValue("Carbon County") CARBON("Carbon County"),
  @XmlEnumValue("Centre County") CENTRE("Centre County"),   
  @XmlEnumValue("Chester County") CHESTER("Chester County"),  
  @XmlEnumValue("Clarion County") CLARION("Clarion County"),
  @XmlEnumValue("Clearfield County") CLEARFIELD("Clearfield County"),   
  @XmlEnumValue("Clinton County") CLINTON("Clinton County"),  
  @XmlEnumValue("Columbia County") COLUMBIA("Columbia County"),   
  @XmlEnumValue("Crawford County") CRAWFORD("Crawford County"),
  @XmlEnumValue("Montgomery County") MONTGOMERY("Montgomery County"),
  @XmlEnumValue("Montour County") MONTOUR("Montour County"),
  @XmlEnumValue("Northampton County") NORTHHAMPTON("Northampton County"),
  @XmlEnumValue("Northumberland County") NORTHUMBERLAND("Northumberland County"),
  @XmlEnumValue("Perry County") PERRY("Perry County"),
  @XmlEnumValue("Philadelphia County") PHILADELPHIA("Philadelphia County"),
  @XmlEnumValue("Pike County") PIKE("Pike County"),
  @XmlEnumValue("Potter County") POTTER("Potter County");
  
  private String prettyString_;
  
  private static Map<String, County> reverseLookup_ = new HashMap<String, County>();
  
  static
  {
    EnumSet<County> countySet = EnumSet.allOf(County.class);
    
    for (County c : countySet)
    {
      reverseLookup_.put(c.prettyString_, c);
    }
    
  }
  
  County(String prettyString)
  {
    prettyString_ = prettyString;
  }

  public static County fromPrettyString(String prettyString)
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
}
