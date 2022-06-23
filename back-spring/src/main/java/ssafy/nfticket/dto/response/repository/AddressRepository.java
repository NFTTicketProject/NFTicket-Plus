package ssafy.nfticket.dto.response.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ssafy.nfticket.entity.Address;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findAddressByShowId(Long showId);
}
