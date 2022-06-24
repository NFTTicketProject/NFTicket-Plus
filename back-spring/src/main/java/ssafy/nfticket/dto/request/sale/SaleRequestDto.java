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
    @NotBlank(message = "유효하지 않은 소개입니다.")
    String description;
    @Max(Integer.MAX_VALUE)
    Integer started_at;
    @Max(Integer.MAX_VALUE)
    Integer ended_at;

}
