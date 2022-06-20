package ssafy.nfticket.dto.show;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SimpleShowDescDto {
    @NotBlank
    private String description;

    public SimpleShowDescDto(String description) { this.description = description; }
}
