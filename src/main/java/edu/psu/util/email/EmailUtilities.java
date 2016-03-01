package edu.psu.util.email;

import java.io.IOException;
import java.net.SocketException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.net.smtp.SMTPClient;
import org.apache.commons.net.smtp.SMTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class EmailUtilities
{
  private static final Logger LOGGER = LoggerFactory.getLogger(EmailUtilities.class);
  
  private EmailUtilities()
  {}

  static final String SMTP_MAIL_HOST = "smtp.psu.edu";
  static final String EMAIL_HOST = "email.psu.edu";
  static final String MAIL_POSTFIX = "@email.psu.edu";

  public static void sendMessage(String sender, String subject, String messageText, String... recipients)
      throws SendFailedException, MessagingException
  {
    if (recipients == null || recipients.length == 0)
    {
      return;
    }

    Properties props = System.getProperties();
    props.setProperty("mail.smtp.host", SMTP_MAIL_HOST);

    MimeMessage message = new MimeMessage(Session.getDefaultInstance(props));
    message.setFrom(new InternetAddress(sender));

    for (int i = 0; i < recipients.length; ++i)
    {
      message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipients[i]));
    }

    message.setSubject(subject);
    message.setText(messageText);

    Transport.send(message);
  }

  public static boolean smtpUserExists(String userid)
  {
    SMTPClient client = new SMTPClient();

    try
    {
      LOGGER.info("Attempting to connect");
      client.connect(EMAIL_HOST);
    }
    catch (SocketException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    catch (IOException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    LOGGER.info("Connect status reploy string: " + client.getReplyString());

    // After connection attempt, you should check the reply code to verify
    // success.
    int reply = client.getReplyCode();
    LOGGER.info("Reply code: " + reply);
     
    if (!SMTPReply.isPositiveCompletion(reply))
    {
      LOGGER.error("SMTP server refused connection.");

      try
      {
        client.disconnect();
      }
      catch (IOException e)
      {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      return false;
    }
    
    int verified = -1;
    LOGGER.info("Post positive connection, going to run vrfy");
    try
    {
      verified = client.vrfy(userid + MAIL_POSTFIX);
      LOGGER.info("vrfy results = " + verified);
    }
    catch (IOException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    return (verified == 250);
  }
}
