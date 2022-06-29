package ssafy.nfticket.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssafy.nfticket.common.util.RandomNickname;
import ssafy.nfticket.dto.request.profile.SimpleProfileInfoDto;
import ssafy.nfticket.repository.ProfileRepository;
import ssafy.nfticket.entity.Profile;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;

    @Transactional
    @Override
    public Profile join(String walletId) {
        Profile newProfile = new Profile();
        RandomNickname randomNickname = new RandomNickname();
        newProfile.setNickname(randomNickname.makeRandomNickname());
        newProfile.setDescription("자기 소개를 입력해주세요!");
        newProfile.setImageUri("none");
        newProfile.setGallery("galleryS");
        newProfile.setWalletId(walletId);

        profileRepository.save(newProfile);
        return newProfile;
    }

    @Override
    public Profile getProfile(String walletAddress) {
        Profile profile = profileRepository.findTop1ByWalletId(walletAddress);
        if (profile != null) {
            return profile;
        } else {
            throw new IllegalStateException("프로필 없음");
        }
    }

    @Transactional
    @Override
    public Profile updateProfile(Profile profile, SimpleProfileInfoDto updatedProfileInfo) {
        profile.setNickname(updatedProfileInfo.getNickname());
        profile.setDescription(updatedProfileInfo.getDescription());
        profile.setImageUri(updatedProfileInfo.getImageUri());
        profileRepository.save(profile);

        return profile;
    }

    @Transactional
    @Override
    public Profile updateNickname(Profile profile, String nickname) {
        profile.setNickname(nickname);
        profileRepository.save(profile);
        return profile;
    }

    @Transactional
    @Override
    public Profile updateDescription(Profile profile, String description) {
        profile.setDescription(description);
        profileRepository.save(profile);
        return profile;
    }

    @Transactional
    @Override
    public Profile updateImageUri(Profile profile, String imageUri) {
        profile.setImageUri(imageUri);
        profileRepository.save(profile);
        return profile;
    }

    @Transactional
    @Override
    public Profile updateGallerySize(Profile profile, String gallery) {
        profile.setGallery(gallery);
        profileRepository.save(profile);
        return profile;
    }

    @Override
    public String getWalletIdByNickname(String nickname) {
        Profile profile = profileRepository.findTop1ByNickname(nickname);
        return profile.getWalletId();
    }
}
