package emailgroupsending.controller;


import emailgroupsending.pojo.EmailRequest;
import emailgroupsending.pojo.Result;
import emailgroupsending.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class EmailController {

    @Autowired
    EmailService emailService;

    @RequestMapping("/email")
    public Result sendEmail(@RequestBody EmailRequest emailRequest){
        String subject = emailRequest.getSubject();
        String text = emailRequest.getText();
        String[] recipients = emailRequest.getRecipients();
        emailService.sendEmail(subject,text,recipients);
        //emailService.sendEmail(subject,text);
        log.info("成功发送邮件给{}",recipients);
        return Result.success("成功发送");
    }
}
