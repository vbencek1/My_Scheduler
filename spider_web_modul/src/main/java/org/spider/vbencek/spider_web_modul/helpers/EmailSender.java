package org.spider.vbencek.spider_web_modul.helpers;

import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {

    private final String username = "dummyproject444@gmail.com";
    private final String password = "pass4444";
    private Session session;

    private void setProperties() {
        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }

    public void sendMessage(String emailTo, String subject, String text) {
        setProperties();
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(emailTo));
            message.setSubject(subject);
            message.setText(text);
            Transport.send(message);
            System.out.println("Email Sender: Message sent!");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public int generateConfirmationCode() {
        Random rand = new Random();
        return rand.nextInt(1000000); 
    }
}
