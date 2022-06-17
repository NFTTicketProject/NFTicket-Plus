package ssafy.nfticket.service;

import org.springframework.stereotype.Service;
import ssafy.nfticket.dto.sale.SaleDto;
import ssafy.nfticket.request.SaleRequestDto;

import java.util.List;

@Service
public interface SaleService {

    public long register(SaleRequestDto saleRequestDto);
    public List<SaleDto> getSaleList();
    public long updateSaleInfo(long saleId, SaleRequestDto saleRequestDto);
}
