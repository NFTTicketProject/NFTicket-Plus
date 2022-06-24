package ssafy.nfticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ssafy.nfticket.entity.Shows;

import java.util.Optional;

@Repository
public interface ShowRepository extends JpaRepository<Shows, Long>, ShowRepositoryCustom {
    Optional<Shows> findById(Long showId);
}
