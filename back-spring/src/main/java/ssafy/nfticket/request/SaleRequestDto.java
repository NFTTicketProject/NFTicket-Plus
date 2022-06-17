package ssafy.nfticket.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class SaleRequestDto {

    @NotNull
    int show_schedule_id;
    @NotBlank(message = "유효하지 않은 소개입니다.")
    String description;
    @Max(Integer.MAX_VALUE)
    int started_at;
    @Max(Integer.MAX_VALUE)
    int ended_at;

}
