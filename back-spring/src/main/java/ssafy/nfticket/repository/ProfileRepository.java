package ssafy.nfticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ssafy.nfticket.entity.Profile;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    Profile findTop1ByWalletId(String walletAddress);
    Profile findTop1ByNickname(String nickname);
}
