package ssafy.nfticket.dto.response.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ssafy.nfticket.entity.Ornament;

public interface OrnamentRepository extends JpaRepository<Ornament, Long> {
}
