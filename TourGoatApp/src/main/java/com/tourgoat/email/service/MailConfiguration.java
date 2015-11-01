/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourgoat.email.service;

//import com.tourgoat.resources.VelocityEngine;
import java.io.IOException;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.ui.velocity.VelocityEngineFactory;

/**
 *
 * @author weynishetzula
 */
@Configuration
public class MailConfiguration {
  @Value("${mail.transport.protocol}")

    private String protocol;

    @Value("${mail.host}")

    private String host;

    @Value("${mail.port}")

    private int port;

    @Value("${mail.smtp.auth}")

    private boolean auth;

    @Value("${mail.smtp.starttls.enable}")

    private boolean starttls;

    @Value("${mail.from}")

    private String from;

    @Value("${mail.username}")

    private String username;

    @Value("${mail.password}")

    private String password;
    @Value("${mail.debug}")

    private String debug;
    
    @Bean
    public JavaMailSender javaMailSender() {

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        Properties mailProperties = new Properties();

        mailProperties.put("mail.smtp.auth", auth);
        mailProperties.put("mail.transport.protocol", protocol);
        mailProperties.put("mail.debug", debug);

        mailProperties.put("mail.smtp.starttls.enable", starttls);
  //      mailProperties.put("mail.smtp.starttls.required", true);
//        mailProperties.put("mail.smtp.EnableSSL.enable","true");
//        mailProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); 
  //      mailProperties.put("mail.smtp.socketFactory.fallbac k", "false"); 
//        mailProperties.put("mail.smtp.socketFactory.class",
//            "javax.net.ssl.SSLSocketFactory");
        mailSender.setJavaMailProperties(mailProperties);

        mailSender.setHost(host);

        mailSender.setPort(port);

        mailSender.setProtocol(protocol);
        Session session=Session.getDefaultInstance(mailProperties,new SmtpAuthenticator(username,password));
        session.setDebug(true);
        mailSender.setSession(session);
        mailSender.setUsername(username);
        mailSender.setDefaultEncoding("UTF-8");
        mailSender.setPassword(password);

        return mailSender;

    }

    private class SmtpAuthenticator extends Authenticator {
	private String username;
	private String password;
	
	public SmtpAuthenticator(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
	}
}
  
}
