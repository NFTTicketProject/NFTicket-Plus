package ssafy.nfticket.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SaleDescriptionRequestDto {

    @NotBlank
    String description;

}
