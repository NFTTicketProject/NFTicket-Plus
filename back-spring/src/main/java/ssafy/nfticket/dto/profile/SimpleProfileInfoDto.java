package ssafy.nfticket.dto.profile;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import ssafy.nfticket.entity.Profile;

import javax.validation.constraints.NotBlank;

@Data
public class SimpleProfileInfoDto {
    @NotBlank(message = "유효하지 않은 닉네임입니다.")
    private String nickname;

    @NotBlank(message = "유효하지 않은 소개입니다.")
    private String description;

    @NotBlank(message = "유효하지 않은 이미지입니다.")
    @JsonProperty(value="image_uri")
    private String imageUri;

    @NotBlank(message = "유효하지 않은 갤러리 타입입니다.")
    private String gallery;

    public SimpleProfileInfoDto(String nickname, String description, String imageUri, String gallery) {
        this.nickname = nickname;
        this.description = description;
        this.imageUri = imageUri;
        this.gallery = gallery;
    }
}
