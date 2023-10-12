package api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateBoardResponse {
    private String id;
    private String name;
    private String desc;
    private Object descData;
    private Boolean closed;
    private String idOrganization;
    private Object idEnterprise;
    private Boolean pinned;
    private String url;
    private String shortUrl;
}
