package ssafy.nfticket.dto.request.profile;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SimpleProfileInfoDto {
    private String nickname;

    private String description;

    @JsonProperty(value="image_uri")
    private String imageUri;

    private String gallery;

    public SimpleProfileInfoDto(String nickname, String description, String imageUri, String gallery) {
        this.nickname = nickname;
        this.description = description;
        this.imageUri = imageUri;
        this.gallery = gallery;
    }
}
