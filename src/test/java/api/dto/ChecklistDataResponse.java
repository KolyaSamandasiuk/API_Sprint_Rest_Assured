package api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChecklistDataResponse {
    public String id;
    public String name;
    public String idBoard;
    public String idCard;
    public Integer pos;
    public ArrayList<Object> checkItems;
}