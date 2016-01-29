package edu.psu.rest;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * This class is intended to provide an adaptor to convert Java Integers's into Strings
 *  in JAXB produced documents.
 * 
 * @author crh5255
 *
 */

public class IntegerAsStringAdapter extends XmlAdapter<String, Integer> {

  @Override
  public String marshal(Integer integer) {
    if (integer == null) {
      return null;
    }

    return integer.toString();
  }

  @Override
  public Integer unmarshal(String integer) throws Exception {
    if (integer == null || integer.isEmpty()) {
      return null;
    }

    return Integer.parseInt(integer);
  }
}