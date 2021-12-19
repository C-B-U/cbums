package com.cbums.common.util;

import com.cbums.config.MailSenderConfig;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
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
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiverEmail));
        message.setSubject(title);
        message.setText(content);

        Transport.send(message);
    }

    public void sendGroupEmail(List<String> receiverEmail, String title, String content) throws MessagingException {
        InternetAddress[] addresses = new InternetAddress[receiverEmail.size()];
        for (int i = 0; i < receiverEmail.size(); i++) {
            addresses[i] = new InternetAddress(receiverEmail.get(i));
        }

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(mailSender.getId()));
        message.addRecipients(Message.RecipientType.TO, addresses);
        message.setSubject(title);
        message.setText(content);

        Transport.send(message);
    }




}
