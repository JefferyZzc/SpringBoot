package emailgroupsending.controller;


import emailgroupsending.pojo.EmailRequest;
import emailgroupsending.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmailController {

    @Autowired
    EmailService emailService;

    @RequestMapping("/email")
    public String sendEmail(@RequestBody EmailRequest emailRequest){
        String subject = emailRequest.getSubject();
        String text = emailRequest.getText();
        List<String> recipients = emailRequest.getRecipients();
        emailService.sendEmail(subject,text,recipients);
        return "Email send successfully";
    }
}
