package ssafy.nfticket.dto.show;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SimpleShowDefaultTicketImgDto {
    @NotBlank
    @JsonProperty(value = "default_ticket_image_uri")
    private String defaultTicketImageUri;

    public SimpleShowDefaultTicketImgDto(String defaultTicketImageUri) { this.defaultTicketImageUri = defaultTicketImageUri; }
}
