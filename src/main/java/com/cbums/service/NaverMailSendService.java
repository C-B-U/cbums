package com.cbums.service;

import com.cbums.config.MailSenderConfig;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class NaverMailSendService {
    MailSenderConfig mailSender = new MailSenderConfig();
    Properties prop;
    Session session;

    public NaverMailSendService() {
        prop = new Properties();
        prop.put("mail.smtp.host", "smtp.naver.com");
        prop.put("mail.smtp.port", 587);
        prop.put("mail.smtp.auth", "true");
        session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailSender.getId(), mailSender.getPassword());
            }
        });
    }

    public void sendEmail(String receiverEmail, String title, String content) throws MessagingException {
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(mailSender.getId()));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress("ktko@ktko.com"));
        message.setSubject(title);
        message.setText(content);

        Transport.send(message);
    }


}
