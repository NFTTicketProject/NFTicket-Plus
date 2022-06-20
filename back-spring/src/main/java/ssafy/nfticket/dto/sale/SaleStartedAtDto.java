package ssafy.nfticket.dto.sale;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SaleStartedAtDto {
    long saleId;
    Integer startedAt;
}
