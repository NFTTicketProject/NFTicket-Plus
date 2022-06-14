package ssafy.nfticket.service;

import org.springframework.stereotype.Service;
import ssafy.nfticket.request.SaleRequestDto;

@Service
public interface SaleService {

    public long register(SaleRequestDto saleRequestDto);
}
