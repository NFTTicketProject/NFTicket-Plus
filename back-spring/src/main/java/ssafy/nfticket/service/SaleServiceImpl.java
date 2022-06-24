package ssafy.nfticket.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssafy.nfticket.dto.request.sale.*;
import ssafy.nfticket.entity.Sale;
import ssafy.nfticket.repository.SaleRepository;
import ssafy.nfticket.common.util.CommonUtils;

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
        sale.setDescription(saleRequestDto.getDescription());
        sale.setStartedAt(saleRequestDto.getStarted_at());
        sale.setEndedAt(saleRequestDto.getEnded_at());
        CommonUtils.saveIfNullId(sale.getId(), saleRepository, sale);
        return sale.getId();
    }

    @Override
    public SaleShowScheduleIdDto getShowSchduleId(long saleId) {
        Sale sale = saleRepository.findById(saleId).orElseGet(Sale::new);
        SaleShowScheduleIdDto saleShowScheduleIdDto = new SaleShowScheduleIdDto();
        saleShowScheduleIdDto.setSale_id(sale.getId());
        saleShowScheduleIdDto.setShow_schedule_id(sale.getShowScheduleId());
        return saleShowScheduleIdDto;
    }

    @Override
    @Transactional
    public SaleShowScheduleIdDto updateShowScheduleId(long saleId, SaleShowScheduleIdRequestDto saleShowScheduleIdRequestDto) {
        Sale sale = saleRepository.findById(saleId).orElseGet(Sale::new);
        sale.setShowScheduleId(saleShowScheduleIdRequestDto.getShow_schedule_id());
        SaleShowScheduleIdDto saleShowScheduleIdDto = new SaleShowScheduleIdDto();
        saleShowScheduleIdDto.setSale_id(sale.getId());
        saleShowScheduleIdDto.setShow_schedule_id(sale.getShowScheduleId());
        CommonUtils.saveIfNullId(sale.getId(), saleRepository, sale);
        return saleShowScheduleIdDto;
    }

    @Override
    public SaleDescriptionDto getDescription(long saleId) {
        Sale sale = saleRepository.findById(saleId).orElseGet(Sale::new);
        SaleDescriptionDto saleDescriptionDto = new SaleDescriptionDto();
        saleDescriptionDto.setSaleId(sale.getId());
        saleDescriptionDto.setDescription(sale.getDescription());
        return saleDescriptionDto;
    }

    @Override
    @Transactional
    public SaleDescriptionDto updateDescription(long saleId, SaleDescriptionRequestDto saleDescriptionRequestDto) {
        Sale sale = saleRepository.findById(saleId).orElseGet(Sale::new);
        sale.setDescription(saleDescriptionRequestDto.getDescription());
        SaleDescriptionDto saleDescriptionDto = new SaleDescriptionDto();
        saleDescriptionDto.setSaleId(sale.getId());
        saleDescriptionDto.setDescription(sale.getDescription());
        CommonUtils.saveIfNullId(sale.getId(), saleRepository, sale);
        return saleDescriptionDto;
    }

    @Override
    public SaleStartedAtDto getStartedAt(long saleId) {
        Sale sale = saleRepository.findById(saleId).orElseGet(Sale::new);
        SaleStartedAtDto saleStartedAtDto = new SaleStartedAtDto();
        saleStartedAtDto.setSaleId(sale.getId());
        saleStartedAtDto.setStartedAt(sale.getStartedAt());
        return saleStartedAtDto;
    }

    @Override
    public SaleEndedAtDto getEndedAt(long saleId) {
        Sale sale = saleRepository.findById(saleId).orElseGet(Sale::new);
        SaleEndedAtDto saleEndedAtDto = new SaleEndedAtDto();
        saleEndedAtDto.setSaleId(sale.getId());
        saleEndedAtDto.setEndedAt(sale.getEndedAt());
        return saleEndedAtDto;
    }

    @Override
    @Transactional
    public SaleStartedAtDto updateStartedAt(long saleId, SaleStartedAtRequestDto saleStartedAtRequestDto) {
        Sale sale = saleRepository.findById(saleId).orElseGet(Sale::new);
        sale.setStartedAt(saleStartedAtRequestDto.getStarted_at());
        SaleStartedAtDto saleStartedAtDto = new SaleStartedAtDto();
        saleStartedAtDto.setSaleId(sale.getId());
        saleStartedAtDto.setStartedAt(sale.getStartedAt());
        CommonUtils.saveIfNullId(sale.getId(), saleRepository, sale);
        return saleStartedAtDto;
    }

    @Override
    @Transactional
    public SaleEndedAtDto updateEndedAt(long saleId, SaleEndedAtRequestDto saleEndedAtRequestDto) {
        Sale sale = saleRepository.findById(saleId).orElseGet(Sale::new);
        sale.setEndedAt(saleEndedAtRequestDto.getEnded_at());
        SaleEndedAtDto saleEndedAtDto = new SaleEndedAtDto();
        saleEndedAtDto.setSaleId(sale.getId());
        saleEndedAtDto.setEndedAt(sale.getEndedAt());
        CommonUtils.saveIfNullId(sale.getId(), saleRepository, sale);
        return saleEndedAtDto;
    }

}
