package ssafy.nfticket.service;

import org.springframework.stereotype.Service;
import ssafy.nfticket.dto.request.ticket.SimpleBlockHashTicketDto;

@Service
public interface BlockService {
    String addBlockHash(SimpleBlockHashTicketDto simpleBlockHashTicketDto);

    String getBlockHash(Long ticketId);
}
