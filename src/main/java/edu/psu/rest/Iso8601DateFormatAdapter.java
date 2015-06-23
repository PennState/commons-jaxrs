package edu.psu.rest;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import edu.psu.Constants;

/**
 * This class is intended to provide an adaptor to convert Java Date's into
 * ISO 8601 formatted time stamps in JAXB produced documents.  
 * 
 * An example of an ISO 8601 Date is 2014-04-15
 * @author ses44
 *
 */

public class Iso8601DateFormatAdapter extends XmlAdapter<String, Date>
{  
  private SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.ISO_8601_DATE_FORMAT);

  @Override
  public String marshal(Date date) 
  {
    if (date == null)
    {
      return null;
    }
    
    return dateFormat.format(date);
  }

  @Override
  public Date unmarshal(String date) throws Exception
  {
    if (date == null || date.isEmpty())
    {
      return null;
    }
    
    return dateFormat.parse(date);
  }
}