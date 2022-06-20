package ssafy.nfticket.dto.simple;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SimpleStaffDto {
    @NotBlank
    private String name;

    @NotBlank
    @JsonProperty(value = "image_uri")
    private String imageUri;

    public SimpleStaffDto(String name, String imageUri) {
        this.name = name;
        this.imageUri = imageUri;
    }
}
