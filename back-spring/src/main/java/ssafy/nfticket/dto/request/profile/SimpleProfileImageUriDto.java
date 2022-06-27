package ssafy.nfticket.dto.request.profile;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class SimpleProfileImageUriDto {
    @JsonProperty(value="image_uri")
    private String imageUri;

    public SimpleProfileImageUriDto(String imageUri) {
        this.imageUri = imageUri;
    }
}
