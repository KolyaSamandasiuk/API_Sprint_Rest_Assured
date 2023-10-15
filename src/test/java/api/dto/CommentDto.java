package api.dto;

import lombok.Data;

import java.util.List;

@Data
public class CommentDto {
    private String id;
    private String idMemberCreator;
    private CommentData data;
    private AppCreator appCreator;
    private String type;
    private String date;
    private Limits limits;
    private Display display;
    private List<Entity> entities;
    private MemberCreator memberCreator;
}
