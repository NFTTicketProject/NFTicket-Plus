package ssafy.nfticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ssafy.nfticket.entity.RandomAdjective;

import java.util.List;

public interface RandomAdjectiveRepository extends JpaRepository<RandomAdjective, Long> {
    List<RandomAdjective> findAll();
}
