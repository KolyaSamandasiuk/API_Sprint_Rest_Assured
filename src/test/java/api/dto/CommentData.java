package api.dto;

import lombok.Data;

@Data
public class CommentData {
    private String text;
    private TextData textData;
    private Card card;
    private Board board;
    private List list;
}
