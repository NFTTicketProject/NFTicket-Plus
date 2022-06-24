package ssafy.nfticket.dto.response.sale;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SaleDescriptionResponseDto {

    @NotBlank
    String description;
}
