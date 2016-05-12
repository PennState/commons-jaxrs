package edu.psu.util.email;

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


import java.io.IOException;
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

import edu.psu.util.email.exception.SmtpConnectionFailedException;

public final class EmailUtilities
{
  private static final Logger LOGGER = LoggerFactory.getLogger(EmailUtilities.class);

  private EmailUtilities()
  {
  }

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

  public static boolean smtpUserExists(String userid) throws IOException, SmtpConnectionFailedException
  {
    SMTPClient client = new SMTPClient();

    LOGGER.debug("Attempting to connect");
    client.connect(EMAIL_HOST);

    LOGGER.debug("Connect status reply string: " + client.getReplyString());

    // After connection attempt, you should check the reply code to verify
    // success.
    int reply = client.getReplyCode();
    LOGGER.debug("Reply code: " + reply);

    if (!SMTPReply.isPositiveCompletion(reply))
    {
      LOGGER.error("SMTP server refused connection.  " + client.getReplyString());

      client.disconnect();

      throw new SmtpConnectionFailedException("Failed to connect " + client.getReplyString());
    }

    int verified = -1;
    LOGGER.debug("Post positive connection, going to run vrfy");

    verified = client.vrfy(userid + MAIL_POSTFIX);
    LOGGER.debug("vrfy results = " + verified);

    return (verified == 250);
  }
}
