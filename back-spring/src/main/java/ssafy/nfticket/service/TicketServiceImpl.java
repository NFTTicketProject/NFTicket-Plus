package ssafy.nfticket.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssafy.nfticket.dto.request.ticket.SimpleTicketDto;
import ssafy.nfticket.entity.Profile;
import ssafy.nfticket.entity.Ticket;
import ssafy.nfticket.repository.ProfileRepository;
import ssafy.nfticket.repository.TicketRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService{

    private final TicketRepository ticketRepository;
    private final ProfileRepository profileRepository;

    @Override
    @Transactional
    public Long addTicket(SimpleTicketDto simpleTicketDto, Profile profile) {
        Ticket ticket = new Ticket();
        ticket.setBlockHash(simpleTicketDto.getBlockHash());
        ticket.setImageUri(simpleTicketDto.getImageUri());
        ticket.setSaleId(simpleTicketDto.getSaleId());
        ticket.setProfile(profile);

        ticketRepository.save(ticket);

        return ticket.getId();
    }

    @Override
    public List<Ticket> getTickets(String walletAddress) {
        Profile profile = profileRepository.findTop1ByWalletId(walletAddress);
        List<Ticket> tickets = ticketRepository.findAllByProfile(profile);
        return tickets;
    }
}
