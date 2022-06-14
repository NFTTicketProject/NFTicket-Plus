package ssafy.nfticket.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ssafy.nfticket.entity.Sale;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long > {

}
