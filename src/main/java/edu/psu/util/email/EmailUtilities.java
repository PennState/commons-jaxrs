package edu.psu.util.email;

import java.util.Properties;
import javax.mail.Session;
import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.InternetAddress;


public final class EmailUtilities
{
  private EmailUtilities()
  {}

  static final String MAIL_HOST = "smtp.psu.edu";

  public static void sendMessage(String sender, String subject, String messageText, String ... recipients) throws AddressException, MessagingException
  {
    if (recipients == null || recipients.length == 0)
    {
      return;
    }

    Properties props = System.getProperties();
    props.setProperty("mail.smtp.host", MAIL_HOST);

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
}
