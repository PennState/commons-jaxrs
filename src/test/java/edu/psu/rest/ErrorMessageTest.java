/*
 * 
 */
package edu.psu.rest;

import javax.ws.rs.core.Response.Status;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

import org.junit.Before;
import org.junit.Test;

public class ErrorMessageTest
{

  /*
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception
  {
  }

  @Test
  public void test() throws JAXBException
  {
    ErrorMessage message = new ErrorMessage(Status.BAD_REQUEST);
    message.addErrorMessage("Validation 1 failed");
    message.addErrorMessage("Validation 2 failed");
    
    JAXBContext jc = JAXBContext.newInstance(ErrorMessage.class);
    Marshaller marshaller = jc.createMarshaller();
    QName qName = new QName("error-message");
    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
    marshaller.marshal(new JAXBElement<ErrorMessage>(qName, ErrorMessage.class, message), System.out);
    //fail("Not yet implemented");
  }

}
