package edu.psu.swe.commons.jaxrs;

import javax.mail.internet.InternetAddress;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.apache.commons.lang3.StringUtils;

public class InternetAddressAdapter extends XmlAdapter<String, InternetAddress> {
  /*
   * * Java => XML * Given the unmappable Java object, return the desired XML
   * representation.
   */
  public String marshal(InternetAddress email) throws Exception {
    if (email == null) {
      return null;
    }
    
    return email.toString();
  }

  /*
   * * XML => Java * Given an XML string, use it to build an instance of the
   * unmappable class.
   */
  public InternetAddress unmarshal(String email) throws Exception {
    if (StringUtils.isEmpty(email)) {
      return null;
    }
    
    InternetAddress[] internetAddresses = InternetAddress.parse(email);
    if (internetAddresses.length == 1) {
      return internetAddresses[0];
    } else {
      return null;
    }
  }
}
