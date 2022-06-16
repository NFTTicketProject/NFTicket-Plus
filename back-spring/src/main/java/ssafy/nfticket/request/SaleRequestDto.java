package ssafy.nfticket.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class SaleRequestDto {

    int showScheduleId;
    String description;
    int startedAt;
    int endedAt;

}
