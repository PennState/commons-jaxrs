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


import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

public class UriUtilities
{
  public static String urlAsString(UriInfo uriInfo, boolean secured) throws MalformedURLException
  {
    UriBuilder builder = uriInfo.getAbsolutePathBuilder();
    URI uri = builder.build();
    URL url;
    
    url = uri.toURL();
    url = new URL("https", url.getHost(), url.getPort(), url.getFile());
    
    return url.toString();
  }
}
