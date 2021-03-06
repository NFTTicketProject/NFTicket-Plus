package ssafy.nfticket.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssafy.nfticket.common.error.CustomException;
import ssafy.nfticket.common.error.ErrorCode;
import ssafy.nfticket.dto.request.ticket.SimpleBlockHashTicketDto;
import ssafy.nfticket.entity.Ticket;
import ssafy.nfticket.repository.TicketRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BlockServiceImpl implements BlockService{

    private final TicketRepository ticketRepository;

    @Transactional
    @Override
    public String addBlockHash(SimpleBlockHashTicketDto simpleBlockHashTicketDto) {
        Ticket ticket = new Ticket();
        ticket.setTicketId(simpleBlockHashTicketDto.getTicketId());
        ticket.setBlockHash(simpleBlockHashTicketDto.getBlockHash());
        ticketRepository.save(ticket);
        return "성공";
    }

    @Override
    public String getBlockHash(Long ticketId) {
        Ticket ticket = ticketRepository.findByTicketId(ticketId).orElseThrow(() ->
                new CustomException(ErrorCode.DATA_NOT_FOUND));

        return ticket.getBlockHash();
    }
}
