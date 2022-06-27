package ssafy.nfticket.service;

import org.springframework.stereotype.Service;
import ssafy.nfticket.dto.request.block.SimpleBlockHashTicketDto;

@Service
public interface BlockService {
    String addBlockHash(SimpleBlockHashTicketDto simpleBlockHashTicketDto);

    String getBlockHash(Long ticketId);
}
