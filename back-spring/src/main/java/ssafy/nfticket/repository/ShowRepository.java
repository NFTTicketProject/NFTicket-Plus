package ssafy.nfticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ssafy.nfticket.entity.Show;

import java.util.Optional;

@Repository
public interface ShowRepository extends JpaRepository<Show, Long>, ShowRepositoryCustom {
    Optional<Show> findById(Long showId);
}
