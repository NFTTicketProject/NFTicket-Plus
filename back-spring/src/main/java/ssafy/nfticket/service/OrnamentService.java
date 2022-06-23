package ssafy.nfticket.service;

import org.springframework.stereotype.Service;
import ssafy.nfticket.dto.ornament.OrnamentDto;
import ssafy.nfticket.dto.request.OrnamentRequestDto;

import java.util.List;

@Service
public interface OrnamentService {
    List<OrnamentDto> getOrnamentList(String walletAddress);

    String register(String walletAddress, OrnamentRequestDto ornamentRequestDto);
}
