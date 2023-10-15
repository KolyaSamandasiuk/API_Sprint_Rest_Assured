package api.dto;

import lombok.Data;

@Data
public class Entity {
    private String type;
    private String id;
    private Boolean hideIfContext;
    private String idContext;
    private String text;
    private String shortLink;
    private String translationKey;
    private String username;
}
