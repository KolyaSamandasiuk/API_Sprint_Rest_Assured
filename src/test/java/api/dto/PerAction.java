package api.dto;

import lombok.Data;

@Data
public class PerAction {
    private String status;
    private Integer disableAt;
    private Integer warnAt;
}
