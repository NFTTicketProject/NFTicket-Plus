package ssafy.nfticket.dto.request.sale;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SaleDescriptionRequestDto {

    @NotBlank
    String description;

}
