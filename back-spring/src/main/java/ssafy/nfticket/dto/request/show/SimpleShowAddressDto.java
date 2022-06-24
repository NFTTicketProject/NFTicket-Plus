package ssafy.nfticket.dto.request.show;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SimpleShowAddressDto {
    @NotBlank
    private String address;
    public SimpleShowAddressDto(String address) { this.address = address; }
}
