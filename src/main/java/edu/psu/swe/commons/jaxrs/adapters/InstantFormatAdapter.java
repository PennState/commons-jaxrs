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


import java.time.Instant;
import java.time.format.DateTimeFormatter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class InstantFormatAdapter extends XmlAdapter<String, Instant>
{
  private static DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_INSTANT;
  
  @Override
  public String marshal(Instant value) throws Exception
  {
	  if (value == null) {
		  return null;
	  }
    return FORMATTER.format(value);
  }

  @Override
  public Instant unmarshal(String s) throws Exception
  {
	  if (s == null || s.trim().isEmpty()) {
		  return null;
	  }
    return Instant.parse(s);
  }
}
