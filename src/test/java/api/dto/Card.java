package api.dto;

import lombok.Data;

@Data
public class Card {
    private String id;
    private String name;
    private int idShort;
    private String shortLink;
}
