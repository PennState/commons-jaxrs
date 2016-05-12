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
