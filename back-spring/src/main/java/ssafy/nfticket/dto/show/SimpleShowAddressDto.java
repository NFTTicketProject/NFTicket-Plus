package ssafy.nfticket.dto.show;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SimpleShowAddressDto {

    @NotBlank
    private Integer showScheduleId;

    @NotBlank
    private String address;

    public SimpleShowAddressDto(Integer showScheduleId, String address) {
        this.showScheduleId = showScheduleId;
        this.address = address;
    }
}
