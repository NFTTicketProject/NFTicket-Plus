package ssafy.nfticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ssafy.nfticket.entity.Profile;
import ssafy.nfticket.entity.Ticket;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    Optional<Ticket> findById(Long ticketId);

    List<Ticket> findAllByProfile(Profile profile);
}
