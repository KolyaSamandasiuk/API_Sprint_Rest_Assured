package api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AttachmentDataResponse {
    private String id;
    private Object bytes;
    private String date;
    private Object edgeColor;
    private String idMember;
    private Boolean isUpload;
    private String mimeType;
    private String name;
    private String url;
    private String fileName;
    private int pos;
}