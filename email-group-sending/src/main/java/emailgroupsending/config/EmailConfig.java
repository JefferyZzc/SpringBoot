package emailgroupsending.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class EmailConfig {

    @Bean
    public JavaMailSender javaMailSender(){
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();


        javaMailSender.setHost("your-smtp-server");
        javaMailSender.setPort(456);
        javaMailSender.setUsername("your-smtp-username");
        javaMailSender.setPassword("your-smtp-password");

        Properties pros = javaMailSender.getJavaMailProperties();
        pros.put("mail.smtp.auth","true");
        pros.put("mail.smtp.starttls.enable","true");
        return javaMailSender;
    }
}
