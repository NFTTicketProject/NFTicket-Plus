package ssafy.nfticket.dto.show;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SimpleShowRunTimeDto {
    @NotBlank
    @JsonProperty(value = "running_time")
    private Integer runningTime;

    public SimpleShowRunTimeDto(Integer runningTime) { this.runningTime = runningTime; }
}
