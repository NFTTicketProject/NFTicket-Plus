package ssafy.nfticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ssafy.nfticket.entity.Show;

@Repository
public interface ShowRepository extends JpaRepository<Show, Long>, ShowRepositoryCustom {
}
