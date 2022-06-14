package ssafy.nfticket.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssafy.nfticket.entity.Sale;
import ssafy.nfticket.repository.SaleRepository;
import ssafy.nfticket.request.SaleRequestDto;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {
    private final SaleRepository saleRepository;

    @Override
    @Transactional
    public long register(SaleRequestDto saleRequestDto) {

        Sale sale = Sale.builder().showScheduleId(saleRequestDto.getShowScheduleId())
                .description(saleRequestDto.getDescription())
                .startedAt(saleRequestDto.getStartedAt())
                .endedAt(saleRequestDto.getEndedAt())
                .build();
        saleRepository.save(sale);

        return 0L;
    }
}
