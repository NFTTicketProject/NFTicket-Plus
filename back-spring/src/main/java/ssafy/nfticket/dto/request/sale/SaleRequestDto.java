package ssafy.nfticket.dto.request.sale;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class SaleRequestDto {

    @NotNull
    Integer show_schedule_id;
    String description;
    Integer started_at;
    Integer ended_at;

}
