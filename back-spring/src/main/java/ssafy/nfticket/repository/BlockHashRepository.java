package ssafy.nfticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ssafy.nfticket.entity.BlockHash;

import java.util.Optional;

@Repository
public interface BlockHashRepository extends JpaRepository<BlockHash, Long> {
    Optional<BlockHash> findByTicketId(Integer ticketId);
}
