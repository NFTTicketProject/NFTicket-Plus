package ssafy.nfticket.dto.profile;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SimpleProfileInfoDto {
    @NotBlank()
    private String nickname;

    @NotBlank()
    private String description;

    @NotBlank()
    @JsonProperty(value="image_uri")
    private String imageUri;

    @NotBlank()
    private String gallery;

    public SimpleProfileInfoDto(String nickname, String description, String imageUri, String gallery) {
        this.nickname = nickname;
        this.description = description;
        this.imageUri = imageUri;
        this.gallery = gallery;
    }
}