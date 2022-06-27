package ssafy.nfticket.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssafy.nfticket.common.error.CustomException;
import ssafy.nfticket.common.error.ErrorCode;
import ssafy.nfticket.dto.request.block.SimpleBlockHashTicketDto;
import ssafy.nfticket.entity.Ticket;
import ssafy.nfticket.repository.TicketRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BlockService {

    private final TicketRepository ticketRepository;

    @Transactional
    public String addBlockHash(SimpleBlockHashTicketDto simpleBlockHashTicketDto) {
        Ticket ticket = ticketRepository.findById(simpleBlockHashTicketDto.getTicketId()).orElseThrow(() ->
                new CustomException(ErrorCode.DATA_NOT_FOUND));
        ticket.setBlockHash(simpleBlockHashTicketDto.getBlockHash());
        ticketRepository.save(ticket);
        return "성공";
    }

    public String getBlockHash(Long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(() ->
                new CustomException(ErrorCode.DATA_NOT_FOUND));

        return ticket.getBlockHash();
    }
}
