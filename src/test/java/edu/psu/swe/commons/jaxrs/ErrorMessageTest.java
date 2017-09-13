package edu.psu.swe.commons.jaxrs;

import java.util.List;
import java.util.Map;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ErrorMessageTest {

  ErrorMessage em;

  @Before 
  public void init() {
    em = new ErrorMessage();
    em.setStatus(Status.INTERNAL_SERVER_ERROR);
    em.setHeader(HttpHeaders.RETRY_AFTER, 900);
    em.setHeader(HttpHeaders.ETAG, "ETag123456789");
  }

  @Test
  public void add_headers_test() {

    Assert.assertNotNull(em.getHeaderMap());
    Assert.assertTrue("The header map does not contain any entries.", em.getHeaderMap().size() > 0);
    Assert.assertTrue("The Retry-After header is missing.", em.getHeaderMap().containsKey(HttpHeaders.RETRY_AFTER));
    Assert.assertTrue("The ETAG header is missing.", em.getHeaderMap().containsKey(HttpHeaders.ETAG));
  }

  @Test
  public void remove_headers_test() {

    Assert.assertNotNull(em.getHeaderMap());
    Assert.assertTrue("The header map does not contain any entries.", em.getHeaderMap().size() > 0);

    Assert.assertEquals("The Retry-After header is missing.", em.removeHeader(HttpHeaders.RETRY_AFTER), 900);
    Assert.assertEquals("The Content-Type header is missing.", em.removeHeader(HttpHeaders.ETAG), "ETag123456789");
    Assert.assertFalse(em.getHeaderMap().containsKey(HttpHeaders.RETRY_AFTER));
    Assert.assertFalse(em.getHeaderMap().containsKey(HttpHeaders.ETAG));

    Assert.assertTrue("The header map is expected to be empty but contains " + em.getHeaderMap().size() + " entries.", em.getHeaderMap().size() == 0);
  }

  @Test
  public void build_response_test() {

    Response response = em.toResponse();
    MultivaluedMap<String, Object> headers = response.getHeaders();

    Assert.assertEquals("Expected response status of " + Status.INTERNAL_SERVER_ERROR.getStatusCode() + " but received " + response.getStatus() + " instead.", response.getStatus(), Status.INTERNAL_SERVER_ERROR.getStatusCode());
    Assert.assertTrue("The response object does not contain the expected Retry-After key.",headers.containsKey(HttpHeaders.RETRY_AFTER));
    Assert.assertTrue("The response object does not contain the ETAG key.", headers.containsKey(HttpHeaders.ETAG));

  }

  @Test
  public void build_response_media_type_test() {
    Response response = em.toResponse("text/plain");
    Map<String, List<Object>> headers = response.getHeaders();
    Assert.assertTrue("The response object does not contain the Content-type key.", headers.containsKey(HttpHeaders.CONTENT_TYPE));

  }

  @Test
  public void build_response_string_media_type_test() {
    Response response = em.toResponse(MediaType.APPLICATION_JSON);
    
    Map<String, List<Object>> headers = response.getHeaders();
    Assert.assertTrue("The response object does not contain the Content-type key.", headers.containsKey(HttpHeaders.CONTENT_TYPE));

  }


}
