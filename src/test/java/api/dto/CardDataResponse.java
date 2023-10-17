package api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CardDataResponse {
    private String id;
    private String name;
    private String address;
    private Boolean closed;
    private String coordinates;
    private String creationMethod;
    private String desc;
    private String due;
    private String dueReminder;
    private String email;
    private String idBoard;
    private String idList;
    private Integer idShort;
    private String locationName;
    private Boolean manualCoverAttachment;
    private Integer pos;
    private String shortLink;
    private String shortUrl;
    private Boolean subscribed;
    private String url;
    private List<String> idChecklists;
}