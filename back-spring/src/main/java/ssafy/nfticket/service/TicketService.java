package ssafy.nfticket.service;

import org.springframework.stereotype.Service;
import ssafy.nfticket.dto.request.ticket.SimpleTicketDto;
import ssafy.nfticket.entity.Profile;
import ssafy.nfticket.entity.Ticket;

import java.util.List;

@Service
public interface TicketService {

    Long addTicket(SimpleTicketDto simpleTicketDto, Profile profile);

    List<Ticket> getTickets(String walletAddress);
}
