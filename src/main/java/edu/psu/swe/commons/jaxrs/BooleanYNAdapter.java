package edu.psu.swe.commons.jaxrs;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class BooleanYNAdapter extends XmlAdapter<String, Boolean> {
  
  private static final String YES = "Y";
  private static final String NO = "N";

  @Override
  public Boolean unmarshal(String v) throws Exception {
    if (v == null) {
      return null;
    }
    if (YES.equalsIgnoreCase(v)) {
      return true;
    }
    return false;
  }

  @Override
  public String marshal(Boolean v) throws Exception {
    if (v == null) {
      return null;
    }
    if (v == true) {
      return YES;
    }
    return NO;
  }

}
