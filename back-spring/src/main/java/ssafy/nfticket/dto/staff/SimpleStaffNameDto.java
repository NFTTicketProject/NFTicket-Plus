package ssafy.nfticket.dto.staff;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class SimpleStaffNameDto {
    @NotBlank()
    private String name;

    public SimpleStaffNameDto(String name) { this.name = name; }
}
