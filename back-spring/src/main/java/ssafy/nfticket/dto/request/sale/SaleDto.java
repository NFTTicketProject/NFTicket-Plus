package ssafy.nfticket.dto.request.sale;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class SaleDto {

    int showScheduleId;

    String description;

    int startedAt;

    @Max(Integer.MAX_VALUE)
    int endedAt;

}

