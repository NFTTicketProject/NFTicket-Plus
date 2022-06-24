package ssafy.nfticket.dto.request.sale;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SaleEndedAtDto {
    long saleId;
    Integer endedAt;
}
