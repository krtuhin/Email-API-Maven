package com.emailapi.email;

import java.io.File;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendEmail {

    //username & password for sender authentication
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

    //method for send email with attachment
    public static void sendAttachement(String message, String subject, String from, String to) {

        //host server of gmail
        String host = "smtp.gmail.com";

        //properties object
        Properties properties = System.getProperties();

        //set email server properties
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", 587);
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", true);

        //get session for sending email
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        });

        //compose message
        Message m = new MimeMessage(session);

        try {
            //set message properties

            //setting sender into message object
            m.setFrom(new InternetAddress(from));

            //setting receiver into message object
            m.setRecipient(Message.RecipientType.TO, new InternetAddress(to));

            //setting subject into message object
            m.setSubject(subject);

            //set attachment
            MimeMultipart mimeMultipart = new MimeMultipart();

            //object for set text
            MimeBodyPart text = new MimeBodyPart();

            //object for set file
            MimeBodyPart file = new MimeBodyPart();

            //set text message
            text.setText(message);

            //file path
            String path = "file path";

            //getting file from path
            File f = new File(path);

            //setting file
            file.attachFile(f);

            //setting two body part into multipart attachment object
            mimeMultipart.addBodyPart(text);
            mimeMultipart.addBodyPart(file);

            //set multipart file into message object
            m.setContent(mimeMultipart);

            //sending email
            Transport.send(m);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
