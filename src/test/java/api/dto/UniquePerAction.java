package api.dto;

import lombok.Data;

@Data
public class UniquePerAction {
    private String status;
    private int disableAt;
    private int warnAt;
}
