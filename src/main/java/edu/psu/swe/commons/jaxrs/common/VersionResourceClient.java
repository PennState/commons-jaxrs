package edu.psu.swe.commons.jaxrs.common;

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


import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status.Family;

public class VersionResourceClient {

  private String baseUrl;

  public VersionResourceClient(String baseUrl) {
    this.baseUrl = baseUrl;
  }

  public Version getVersion() {
    Client client = ClientBuilder.newClient();

    WebTarget target = client.target(baseUrl).path("version");

    Response response =  target.request().accept("application/json").get();
    
    if (response.getStatusInfo().getFamily() != Family.SUCCESSFUL) {
      // TODO handle retry on expired tokens
      throw new WebApplicationException(response);
    }

    return response.readEntity(Version.class);
  }

}
