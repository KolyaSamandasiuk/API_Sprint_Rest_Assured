package api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmojiDataResponse {
        public String unified;
        public String name;
        @JsonProperty("native")
        public String mynative;
        public String shortName;
        public ArrayList<String> shortNames;
        public String text;
        public ArrayList<String> texts;
        public String category;
        public int sheetX;
        public int sheetY;
        public String tts;
        public ArrayList<String> keywords;
}

