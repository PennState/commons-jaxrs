package edu.psu.swe.commons.jaxrs.adapters;

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


import java.time.MonthDay;
import java.time.format.DateTimeFormatter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class MonthDayAdapter extends XmlAdapter<String, MonthDay> {

  private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MM-dd");

  @Override
  public MonthDay unmarshal(String v) throws Exception {
    if (v == null) {
      return null;
    }
    return MonthDay.parse(v, FORMATTER);
  }

  @Override
  public String marshal(MonthDay v) throws Exception {
    if (v == null) {
      return null;
    }
    return FORMATTER.format(v);
  }

}
