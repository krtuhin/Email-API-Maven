package com.emailapi.email;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {

    //authentication username and password
    private static String userName = "sender email address without '@gmail.com'";
    private static String password = "app password created in gmail account";

    //this is the method for send email
    public static void sendTxst(String message, String subject, String to, String from) {

        //variable for gmail host
        String host = "smtp.gmail.com";

        //get system properties
        Properties properties = System.getProperties();

        //set important information into properties object
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        //Step 1: get the session object
        Session session = Session.getInstance(properties, new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {

                //email authentication
                return new PasswordAuthentication(userName, password);
            }
        });

        session.setDebug(true);

        //Step 2: ompose the message [text, multimedia, etc]
        MimeMessage mimeMessage = new MimeMessage(session);

        try {
            //sender
            mimeMessage.setFrom(new InternetAddress(from));

            //receiver
            mimeMessage.setRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));

            //message subject
            mimeMessage.setSubject(subject);

            //mesage text
            mimeMessage.setText(message);

            //Step 3: Send the message using Transport class
            Transport.send(mimeMessage);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

}
