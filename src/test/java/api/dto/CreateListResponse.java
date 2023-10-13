package api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateListResponse {
    private String id;
    private String name;
    private Boolean closed;
    private Integer pos;
    private String softLimit;
    private String idBoard;
}
