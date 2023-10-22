package emailgroupsending.service.impl;


import emailgroupsending.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;
    @Override
    public void sendEmail(String subject, String text, String[] recipients) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("1045288412@qq.com");
        message.setSubject(subject);
        message.setText(text);
        message.setTo(recipients);

        javaMailSender.send(message);
    }
}
