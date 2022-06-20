package ssafy.nfticket.dto.show;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SimpleShowVideoUriDto {
    @NotBlank
    @JsonProperty(value = "video_uri")
    private String videoUri;

    public SimpleShowVideoUriDto(String videoUri) { this.videoUri = videoUri; }
}
