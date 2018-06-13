package com.teamc.mail;

import lombok.extern.slf4j.Slf4j;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Slf4j
public class Mail {

    public static void sendMail(String to, String messageSubject, String messageBody) {
        //String to = "Roman.POLOVINTSEV@raiffeisen.ru";
        String from = "Test";
        String host = "smtp.raiffeisen.ru";

        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);

        Session session = Session.getDefaultInstance(properties);

        try {
            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(messageSubject);
            message.setText(messageBody);

            Transport.send(message);

            log.info("Email Sent successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
