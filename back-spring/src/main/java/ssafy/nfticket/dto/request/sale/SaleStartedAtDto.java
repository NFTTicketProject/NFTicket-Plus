package ssafy.nfticket.dto.request.sale;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class SaleStartedAtDto {
    Long saleId;
    Integer startedAt;
}
