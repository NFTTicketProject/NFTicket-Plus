package ssafy.nfticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ssafy.nfticket.entity.RandomNoun;

import java.util.List;

public interface RandomNounRepository extends JpaRepository<RandomNoun, Long> {
    List<RandomNoun> findAll();
}
