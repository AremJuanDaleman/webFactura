/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.factura.envio;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.internet.MimeMessage;
/**
 *
 * @author danie
 */
public class EnvioCorreoFacturas {
    
    public static void send(String f){
       try{
           String host = "smtp.gmail.com";
           String user = "aremprueba@gmail.com";
           String pass = "arem2018-1";
           String to = "aremprueba@gmail.com";
           String from = "aremprueba@gmail.com";
           String subject = "FACTURA ELECTRONICA";
           String messageText = f;
           boolean sessionDebug = false;
           
           Properties props = System.getProperties();
           
           props.put("mail.smtp.starttls.enable", "true");
           props.put("mail.smtp.host", host);
           props.put("mail.smtp.port", "587");
           props.put("mail.smtp.auth", "true");
           props.put("mail.smtp.starttls.required", "true");
           
           java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
           Session mailSession = Session.getDefaultInstance(props,null);
           mailSession.setDebug(sessionDebug);
           Message msg = new MimeMessage(mailSession);
           msg.setFrom(new InternetAddress(from));
           InternetAddress[] address = {new InternetAddress(to)};
           msg.setRecipients(Message.RecipientType.TO, address);
           msg.setSubject(subject); msg.setSentDate(new Date());
           msg.setText(messageText);
           
           Transport transport = mailSession.getTransport("smtp");
           transport.connect(host, user, pass);
           transport.sendMessage(msg, msg.getAllRecipients());
           transport.close();
           System.out.println("Mensaje enviado correctamente");                                                       
           
       }catch(Exception ex){
           System.out.println(ex);
       }
    }
	
	
 
	
 
	
    

}
