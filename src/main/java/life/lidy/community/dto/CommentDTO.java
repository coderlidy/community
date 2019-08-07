package life.lidy.community.dto;

import lombok.Data;
import org.springframework.stereotype.Controller;

@Data
public class CommentDTO {
    private Long parentId;
    private String content;
    private Integer type;
}
