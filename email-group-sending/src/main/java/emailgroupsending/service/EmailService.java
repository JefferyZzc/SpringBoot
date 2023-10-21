package emailgroupsending.service;

import java.util.List;

public interface EmailService {
    void sendEmail(String subject,String text, String[] recipients);
    //void sendEmail(String subject,String text);
}
