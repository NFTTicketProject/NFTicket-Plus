package ssafy.nfticket.service;

import org.springframework.stereotype.Service;
import ssafy.nfticket.dto.request.*;
import ssafy.nfticket.dto.sale.*;

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

    SaleStartedAtDto getStartedAt(long saleId);

    SaleEndedAtDto getEndedAt(long saleId);

    SaleStartedAtDto updateStartedAt(long saleId, SaleStartedAtRequestDto saleStartedAtRequestDto);

    SaleEndedAtDto updateEndedAt(long saleId, SaleEndedAtRequestDto saleEndedAtRequestDto);
}
