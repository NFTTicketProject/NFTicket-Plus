package ssafy.nfticket.dto.response.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ssafy.nfticket.entity.Staff;

import java.util.List;
import java.util.Optional;

public interface StaffRepository extends JpaRepository<Staff, Long> {
    Optional<Staff> findStaffById(Long staffId);
    List<Staff> findAll();
}
