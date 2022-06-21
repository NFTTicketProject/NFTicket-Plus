package ssafy.nfticket.dto.show;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class SimpleShowAddressScheduleDto {

    @NotNull
    @JsonProperty(value = "show_schedule_id")
    private Integer showScheduleId;

    @NotBlank
    private String address;

    public SimpleShowAddressScheduleDto(Integer showScheduleId, String address) {
        this.showScheduleId = showScheduleId;
        this.address = address;
    }
}
