package ssafy.nfticket.service;

import org.springframework.stereotype.Service;
import ssafy.nfticket.dto.request.profile.SimpleProfileInfoDto;
import ssafy.nfticket.entity.Profile;

@Service
public interface ProfileService {
    Profile join(String walletId);
    Profile getProfile(String walletId);
    Profile updateProfile(Profile profile, SimpleProfileInfoDto simpleProfileInfoDto);
    Profile updateNickname(Profile profile, String nickname);
    Profile updateDescription(Profile profile, String description);
    Profile updateImageUri(Profile profile, String imageUri);
    Profile updateGallerySize(Profile profile, String gallery);
    String getWalletIdByNickname(String nickname);


}
