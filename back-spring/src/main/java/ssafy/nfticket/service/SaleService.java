package ssafy.nfticket.service;

import org.springframework.stereotype.Service;
import ssafy.nfticket.dto.request.SaleDescriptionRequestDto;
import ssafy.nfticket.dto.request.SaleShowScheduleIdRequestDto;
import ssafy.nfticket.dto.sale.SaleDescriptionDto;
import ssafy.nfticket.dto.sale.SaleShowScheduleIdDto;
import ssafy.nfticket.dto.sale.SaleDto;
import ssafy.nfticket.dto.request.SaleRequestDto;

import java.util.List;

@Service
public interface SaleService {

    long register(SaleRequestDto saleRequestDto);
    List<SaleDto> getSaleList();
    long updateSaleInfo(long saleId, SaleRequestDto saleRequestDto);

    SaleShowScheduleIdDto getShowSchduleId(long saleId);

    SaleShowScheduleIdDto updateShowScheduleId(long saleId, SaleShowScheduleIdRequestDto saleShowScheduleIdRequestDto);

    SaleDescriptionDto getDescription(long saleId);

    SaleDescriptionDto updateDescription(long saleId, SaleDescriptionRequestDto saleDescriptionRequestDto);
}
