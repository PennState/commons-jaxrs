package edu.psu.rest;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * This class is intended to provide an adaptor to convert Java Double's into Strings
 *  in JAXB produced documents.
 * 
 * @author crh5255
 *
 */

public class DoubleAsStringAdapter extends XmlAdapter<String, Double> {

  @Override
  public String marshal(Double d) {
    if (d == null) {
      return null;
    }

    return d.toString();
  }

  @Override
  public Double unmarshal(String d) throws Exception {
    if (d == null || d.isEmpty()) {
      return null;
    }

    return Double.parseDouble(d);
  }
}