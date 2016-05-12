package edu.psu.swe.commons.jaxrs.common;

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
