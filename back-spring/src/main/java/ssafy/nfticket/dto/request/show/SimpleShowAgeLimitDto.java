package ssafy.nfticket.dto.request.show;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SimpleShowAgeLimitDto {
    @NotBlank
    @JsonProperty(value = "age_limit")
    private Integer ageLimit;

    public SimpleShowAgeLimitDto(Integer ageLimit) { this.ageLimit = ageLimit; }
}
