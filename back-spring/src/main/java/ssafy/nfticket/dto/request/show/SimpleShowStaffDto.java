package ssafy.nfticket.dto.request.show;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SimpleShowStaffDto {
    @NotBlank
    private String staff;

    public SimpleShowStaffDto(String staff) { this.staff = staff; }
}
