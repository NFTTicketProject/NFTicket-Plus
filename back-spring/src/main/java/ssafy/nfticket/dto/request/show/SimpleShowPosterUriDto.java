package ssafy.nfticket.dto.request.show;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SimpleShowPosterUriDto {
    @NotBlank
    @JsonProperty(value = "poster_uri")
    private String posterUri;

    public SimpleShowPosterUriDto(String posterUri) { this.posterUri = posterUri; }
}
