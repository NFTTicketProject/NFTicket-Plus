package ssafy.nfticket.service;

import org.springframework.stereotype.Service;
import ssafy.nfticket.dto.request.ornament.OrnamentDto;
import ssafy.nfticket.dto.request.ornament.OrnamentRequestDto;

import java.util.List;

@Service
public interface OrnamentService {
    List<OrnamentDto> getOrnamentList(String walletAddress);

    String register(String walletAddress, OrnamentRequestDto ornamentRequestDto);
}
