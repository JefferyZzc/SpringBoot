package emailgroupsending.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailRequest {
    private String subject;
    private String text;
    private String[]  recipients;
}
