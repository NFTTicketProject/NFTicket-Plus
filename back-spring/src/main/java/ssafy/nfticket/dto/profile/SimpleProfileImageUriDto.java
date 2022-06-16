package ssafy.nfticket.dto.profile;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SimpleProfileImageUriDto {
    @NotBlank(message = "유효하지 않은 이미지입니다.")
    private String imageUri;

    public SimpleProfileImageUriDto(String imageUri) {
        this.imageUri = imageUri;
    }
}
