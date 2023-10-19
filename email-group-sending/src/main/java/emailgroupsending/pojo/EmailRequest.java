package emailgroupsending.pojo;

import lombok.Data;

import java.util.List;

@Data
public class EmailRequest {
    private String subject;
    private String text;
    private List<String> recipients;
}
