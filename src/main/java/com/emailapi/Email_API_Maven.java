package com.emailapi;

import com.emailapi.email.SendEmail;

public class Email_API_Maven {

    public static void main(String[] args) {

        System.out.println("Sending Email...!");

        String message = "Hello! This message for sending attachment..!";
        String subject = "Verification Mail";
        String to = "receiver email address";
        String from = "sender email address";

        //sending only text
        SendEmail.sendTxst(message, subject, to, from);

        //sending attachment with text
        SendEmail.sendAttachement(message, subject, from, to);

        System.out.println("Email sent successfully...!!");

    }
}
