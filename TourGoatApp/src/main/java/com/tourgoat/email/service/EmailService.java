/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourgoat.email.service;
import com.tourgoat.resources.VelocityEngineTemplate;
import com.tourgoat.users.models.User;
import java.util.HashMap;
import java.util.Map;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;
/**
 *
 * @author weynishetzula
 */
@Service("mailService")
public class EmailService {
   @Autowired
     private MailConfiguration mailConfiguration;
     @Autowired
     private VelocityEngineTemplate velocityEngineTemplate;
     
    EmailService() {

    }
//
//    /**
//     * This method will send compose and send the message
//     *
//     * @param to
//     * @param subject
//     * @param body
//     * @return  */
//    public SimpleMailMessage sendMail(String to, String subject, String body)
//    {
//        SimpleMailMessage mailMessage = new SimpleMailMessage();
//
//        mailMessage.setTo("someone@localhost");
//
//        mailMessage.setReplyTo("someone@localhost");
//
//        mailMessage.setFrom("someone@localhost");
//
//        mailMessage.setSubject("Lorem ipsum");
//
//        mailMessage.setText("Lorem ipsum dolor sit amet [...]");
//
//        mailConfiguration.javaMailSender().send(mailMessage);
//
//        return mailMessage;
//
//    }
 
    public void sendConfirmationEmail(String to,String fullName) {
        User user=new User();
        user.setFullName(fullName);
        user.setEmail(to);
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                message.setTo(user.getEmail());
                message.setSubject("Tour Goat Verification");
                message.setFrom(to); // could be parameterized...
                Map model = new HashMap();
                model.put("user", user);
                String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngineTemplate.getVelocityEngine(), "templates/registration-confirmation.vm", "UTF-8", model);
                message.setText(text, true);
            }
        };
        JavaMailSender javaMailSender=this.mailConfiguration.javaMailSender();
        javaMailSender.send(preparator);
    }  
}
