package edu.psu.swe.commons.jaxrs;

import javax.ws.rs.core.Response;

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
