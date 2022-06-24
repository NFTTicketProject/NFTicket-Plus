package ssafy.nfticket.dto.request.sale;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class SaleDto {

    Integer showScheduleId;

    String description;

    Integer startedAt;

    Integer endedAt;

}

