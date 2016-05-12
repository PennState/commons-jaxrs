package edu.psu.swe.commons.jaxrs;

/*
 * The Pennsylvania State University © 2016
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */


import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import edu.psu.Constants;

/**
 * This class is intended to provide an adaptor to convert Java Date's into
 * ISO 8601 formatted time stamps in JAXB produced documents.  
 * 
 * An example of an ISO 8601 Time Stamp is 2014-04-15T08:18:44+00:00
 * @author ses44
 *
 */

public class Iso8601DateTimeFormatAdapter extends XmlAdapter<String, Date>
{
  
  private SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.ISO_8601_DATE_TIME_FORMAT);

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
