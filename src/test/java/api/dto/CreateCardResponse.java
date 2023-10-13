package api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateCardResponse {
    private String id;
    private String name;
    private String idBoard;
    private String idList;
}
