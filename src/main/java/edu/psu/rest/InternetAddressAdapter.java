package edu.psu.rest;

import javax.mail.internet.InternetAddress;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.apache.commons.lang3.StringUtils;

public class InternetAddressAdapter extends XmlAdapter<String, InternetAddress> {
  /*
   * * Java => XML * Given the unmappable Java object, return the desired XML
   * representation.
   */
  public String marshal(InternetAddress dbData) throws Exception {
    if (dbData == null) {
      return null;
    }
    
    return dbData.getAddress();
  }

  /*
   * * XML => Java * Given an XML string, use it to build an instance of the
   * unmappable class.
   */
  public InternetAddress unmarshal(String attribute) throws Exception {
    if (StringUtils.isEmpty(attribute)) {
      return null;
    }
    
    InternetAddress ia = new InternetAddress();
    ia.setAddress(attribute);
    return ia;
  }
}
