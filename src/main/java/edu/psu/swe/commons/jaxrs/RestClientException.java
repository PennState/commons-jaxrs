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


import javax.ws.rs.ProcessingException;
import javax.ws.rs.core.Response;


public class RestClientException extends Exception
{
  private static final long serialVersionUID = 7360783673606191576L;

  private int statusCode_;
  private ErrorMessage errorMessage_;
  
  public RestClientException(Response response)
  {
    statusCode_ = response.getStatus();
    try {
      errorMessage_ = response.readEntity(ErrorMessage.class);
    } catch (ProcessingException e) {
      errorMessage_ = null;
    }
  }
  
  public RestClientException(int statusCode, ErrorMessage errorMessage)
  {
    statusCode_ = statusCode;
    errorMessage_ = errorMessage;
  }
  
  public int getStatusCode()
  {
    return statusCode_;
  }
  
  public ErrorMessage getErrorMessage()
  {
    return errorMessage_;
  }
  
  @Override
  public String getMessage(){
    String message = "Rest Client Exception: Status Code: " + statusCode_ + " ";
    if(errorMessage_ != null){
      message += "Error Messages: " + errorMessage_.getErrorMessageList();
    }
    
    return message;
    
  }
  
}
