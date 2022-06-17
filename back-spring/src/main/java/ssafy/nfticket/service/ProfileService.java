package ssafy.nfticket.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssafy.nfticket.dto.profile.SimpleProfileInfoDto;
import ssafy.nfticket.entity.RandomAdjective;
import ssafy.nfticket.entity.RandomNoun;
import ssafy.nfticket.repository.ProfileRepository;
import ssafy.nfticket.entity.Profile;
import ssafy.nfticket.repository.RandomAdjectiveRepository;
import ssafy.nfticket.repository.RandomNounRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final RandomAdjectiveRepository randomAdjectiveRepository;
    private final RandomNounRepository randomNounRepository;

    @Transactional
    public Profile join(String walletId) {
        Profile newProfile = new Profile();
        newProfile.setNickname(getRandomNickname());
        newProfile.setDescription("자기 소개를 입력해주세요!");
        newProfile.setImageUri("none");
        newProfile.setGallery("galleryS");
        newProfile.setWalletId(walletId);

        profileRepository.save(newProfile);
        return newProfile;
    }

    public Profile getProfile(String walletAddress) {
        Profile profile = profileRepository.findTop1ByWalletId(walletAddress);
        if (profile != null) {
            return profile;
        } else {
            throw new IllegalStateException("프로필 없음");
        }
    }

    public String getRandomNickname() {
        List<RandomAdjective> randomAdjectiveList = randomAdjectiveRepository.findAll();
        List<RandomNoun> randomNounList = randomNounRepository.findAll();
        String adjective = randomAdjectiveList.get((int) (Math.random() * randomAdjectiveList.size())).getAdjective();
        String noun = randomNounList.get((int) (Math.random() * randomNounList.size())).getNoun();
        return adjective + " " + noun;
    }

    @Transactional
    public Profile updateProfile(Profile profile, SimpleProfileInfoDto updatedProfileInfo) {
        profile.setNickname(updatedProfileInfo.getNickname());
        profile.setDescription(updatedProfileInfo.getDescription());
        profile.setImageUri(updatedProfileInfo.getImageUri());
        profile.setGallery(updatedProfileInfo.getGallery());
        profileRepository.save(profile);

        return profile;
    }

    @Transactional
    public Profile updateNickname(Profile profile, String nickname) {
        profile.setNickname(nickname);
        profileRepository.save(profile);
        return profile;
    }

    @Transactional
    public Profile updateDescription(Profile profile, String description) {
        profile.setDescription(description);
        profileRepository.save(profile);
        return profile;
    }

    @Transactional
    public Profile updateImageUri(Profile profile, String imageUri) {
        profile.setImageUri(imageUri);
        profileRepository.save(profile);
        return profile;
    }
}
