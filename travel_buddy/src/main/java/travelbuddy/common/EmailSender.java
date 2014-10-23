package travelbuddy.common;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailSender {

    public static void main(String[] args) {
        // SMTP info
        String host = "smtp.gmail.com";
        String port = "587";
        //considering the info like user email will be taken from DB
        Scanner user_input = new Scanner(System.in);
        String From;
        System.out.print("Please Enter your email address: ");
        From = user_input.next();
        //String From = "travelbuddytest@gmail.com";
        //String password = "travelbuddytest123";
        String password;
        System.out.print("Please Enter your password: ");
        password = user_input.next();

        // message info
        //String To = "recipient email";
        String To;
        System.out.print("Please Enter the recipient email address: ");
        To = user_input.next();
        String subject = "New email with attachments for testing";
        String message = "This email just for testing, thanks.";

        // attachments just for test
        String[] attachments = new String[1];
        //here maybe we put some package info from DB (file to inform user with what he/she buy)
        attachments[0] = "C:/Users/khaled/Documents/NetBeansProjects/GmailEmail/src/attachment_test.txt";

        try {
            sendEmailFunctionality(host, port, From, password, To,
                    subject, message, attachments);
            System.out.println("Email have been sent successfully.");
        } catch (Exception ex) {
            System.out.println("Failure to send the email.");
            ex.printStackTrace();
        }
    }

    public static void sendEmailFunctionality(String host, String port,
            final String userName, final String password, String toAddress,
            String subject, String message, String[] attachments)
            throws AddressException, MessagingException {
        // sets SMTP server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.user", userName);
        properties.put("mail.password", password);

        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        };
        Session session = Session.getInstance(properties, auth);

        // creates a new e-mail message
        Message sessionmessage = new MimeMessage(session);

        sessionmessage.setFrom(new InternetAddress(userName));
        InternetAddress[] toAddresses = {new InternetAddress(toAddress)};
        sessionmessage.setRecipients(Message.RecipientType.TO, toAddresses);
        sessionmessage.setSubject(subject);
        sessionmessage.setSentDate(new Date());

        // creates message part
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(message, "text/html");

        // creates multi-part
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);

        // adds attachments
        if (attachments != null && attachments.length > 0) {
            for (String filePath : attachments) {
                MimeBodyPart attachPart = new MimeBodyPart();

                try {
                    attachPart.attachFile(filePath);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                multipart.addBodyPart(attachPart);
            }
        }

        // sets the multi-part as e-mail's content
        sessionmessage.setContent(multipart);

        // sends the e-mail
        Transport.send(sessionmessage);

    }

}
