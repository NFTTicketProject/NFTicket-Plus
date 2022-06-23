package ssafy.nfticket.dto.show;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SimpleShowNameDto {
    @NotBlank
    private String name;

    public SimpleShowNameDto(String name) { this.name = name; }
}
