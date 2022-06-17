package ssafy.nfticket.dto.request;

import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class SaleShowScheduleIdRequestDto {

    @NotNull
    Integer show_schedule_id;

}
