package ssafy.nfticket.dto.profile;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SimpleProfileGalleryDto {
    @NotBlank(message = "유효하지 않은 갤러리 타입입니다.")
    private String gallery;

    public SimpleProfileGalleryDto(String gallery) {
        this.gallery = gallery;
    }
}
