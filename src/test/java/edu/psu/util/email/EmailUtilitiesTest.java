package edu.psu.util.email;

import static org.junit.Assert.fail;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EmailUtilitiesTest 
{
  @Before
  public void setUp() throws Exception 
  {
  }

  @After
  public void tearDown() 
  {
  }

  /*
    * Test method for {@link ait.common.email.EmailUtilities#sendMessage(java.lang.String, java.lang.String, java.lang.String, java.lang.String[])
  */
  @Test
  public void testSendMessageNullSender() 
  {
    try
    {
      EmailUtilities.sendMessage(null, "Hello", "ses44@psu.edu");
    }
    catch(java.lang.NullPointerException npe)
    {
      //This is what we expect
    }
    catch(AddressException ae)
    {
      fail("Didn't expect and Address Exception");
    }
    catch(MessagingException me)
    {
      fail("Didn't expect and Messaging Exception");
    }
  }
}
