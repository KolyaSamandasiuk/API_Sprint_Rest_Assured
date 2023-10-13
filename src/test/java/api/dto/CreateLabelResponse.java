package api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateLabelResponse {
    public String id;
    public String idBoard;
    public String name;
    public String color;
    @JsonProperty("uses")
    public int myuses;
    public CreateLabelResponse limits;
}