package ssafy.nfticket.dto.profile;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SimpleProfileGalleryDto {
    @NotBlank()
    private String gallery;

    public SimpleProfileGalleryDto(String gallery) {
        this.gallery = gallery;
    }
}
