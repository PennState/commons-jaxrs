package edu.psu.swe.commons.jaxrs;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.Response;

import edu.psu.swe.commons.jaxrs.exceptions.RestClientException;

/**
 * Corresponds to {@link java.util.function.Function} but specific to REST calls.
 */
@FunctionalInterface
public interface RestCall {
  Response apply(Invocation request) throws RestClientException;
}
