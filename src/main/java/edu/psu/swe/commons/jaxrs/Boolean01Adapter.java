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


import javax.xml.bind.annotation.adapters.XmlAdapter;

public class Boolean01Adapter extends XmlAdapter<String, Boolean> {
  
  private static final String YES = "1";
  private static final String NO = "0";

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
