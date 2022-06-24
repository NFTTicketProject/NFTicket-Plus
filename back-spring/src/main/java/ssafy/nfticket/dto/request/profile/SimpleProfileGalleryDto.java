package ssafy.nfticket.dto.request.profile;

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
