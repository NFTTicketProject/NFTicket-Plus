package ssafy.nfticket.dto.sale;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class SaleDto {

    int showScheduleId;

    @NotBlank(message = "유효하지 않은 소개입니다.")
    String description;

    int startedAt;

    int endedAt;

}

