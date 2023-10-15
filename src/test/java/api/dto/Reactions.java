package api.dto;

import lombok.Data;

@Data
public class Reactions {
    private PerAction perAction;
    private UniquePerAction uniquePerAction;
}
