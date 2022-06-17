package ssafy.nfticket.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssafy.nfticket.dto.sale.SaleDto;
import ssafy.nfticket.entity.Sale;
import ssafy.nfticket.repository.SaleRepository;
import ssafy.nfticket.request.SaleRequestDto;
import ssafy.nfticket.util.CommonUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {
    private final SaleRepository saleRepository;

    @Override
    @Transactional
    public long register(SaleRequestDto saleRequestDto) {

        Sale sale = Sale.builder().showScheduleId(saleRequestDto.getShow_schedule_id())
                .description(saleRequestDto.getDescription())
                .startedAt(saleRequestDto.getStarted_at())
                .endedAt(saleRequestDto.getEnded_at())
                .build();
        Sale saleObj = saleRepository.save(sale);
        saleRepository.flush();

        return saleObj.getId();

    }

    @Override
    @Transactional
    public List<SaleDto> getSaleList() {

        List<Sale> saleList = saleRepository.findAll();
        List<SaleDto> saleDtoList = new ArrayList<>();
        for(Sale sale : saleList){
            SaleDto saleDto = new SaleDto();
            saleDto.setShowScheduleId(sale.getShowScheduleId());
            saleDto.setDescription(sale.getDescription());
            saleDto.setStartedAt(sale.getStartedAt());
            saleDto.setEndedAt(sale.getEndedAt());
            saleDtoList.add(saleDto);
        }

        return saleDtoList;

    }

    @Override
    @Transactional
    public long updateSaleInfo(long saleId, SaleRequestDto saleRequestDto) {
        Sale sale = saleRepository.findById(saleId).orElseGet(Sale::new);
        sale.setShowScheduleId(saleRequestDto.getShow_schedule_id());
        sale.setDescription(saleRequestDto.getDescription());
        sale.setStartedAt(saleRequestDto.getStarted_at());
        sale.setEndedAt(saleRequestDto.getEnded_at());
        CommonUtils.saveIfNullId(sale.getId(), saleRepository, sale);
        saleRepository.flush();
        return sale.getId();
    }

}
