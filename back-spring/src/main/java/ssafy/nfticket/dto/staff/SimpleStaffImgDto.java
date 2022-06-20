package ssafy.nfticket.dto.staff;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class SimpleStaffImgDto {
    @NotBlank
    @JsonProperty(value = "image_uri")
    private String imageUri;

    public SimpleStaffImgDto(String imageUri) { this.imageUri = imageUri; }
}
