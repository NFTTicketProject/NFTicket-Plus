package ssafy.nfticket.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssafy.nfticket.repository.ProfileRepository;
import ssafy.nfticket.entity.Profile;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProfileService {

    private final ProfileRepository profileRepository;

    @Transactional
    public Profile join(String walletId) {
        Profile newProfile = new Profile();
        newProfile.setNickname("통통한 너구리");
        newProfile.setDescription("너구리 한마리 어쩌구");
        newProfile.setImageUri("임시");
        newProfile.setGallery("gallery5");
        newProfile.setWalletId(walletId);

        profileRepository.save(newProfile);
        return newProfile;
    }

}
