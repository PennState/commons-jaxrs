package edu.psu.swe.commons.jaxrs.utilities;

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


import javax.ws.rs.core.Response;

import edu.psu.swe.commons.jaxrs.exceptions.RestClientException;

public class RestClientUtil {

  private RestClientUtil() {
    
  }
  
  public static void checkForSuccess(Response response) throws RestClientException {
    Response.Status.Family responseFamily = response.getStatusInfo().getFamily();
    
    if (responseFamily.equals(Response.Status.Family.CLIENT_ERROR)||
        responseFamily.equals(Response.Status.Family.SERVER_ERROR))
    {
      throw new RestClientException(response);
    }
  }
  
}
