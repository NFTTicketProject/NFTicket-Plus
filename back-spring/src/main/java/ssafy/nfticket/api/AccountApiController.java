package ssafy.nfticket.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ssafy.nfticket.dto.request.profile.SimpleProfileImageUriDto;
import ssafy.nfticket.dto.request.profile.SimpleProfileInfoDto;
import ssafy.nfticket.dto.request.profile.SimpleProfileDescDto;
import ssafy.nfticket.dto.request.profile.SimpleProfileNicknameDto;
import ssafy.nfticket.entity.Profile;
import ssafy.nfticket.service.ProfileService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountApiController {

    private final ProfileService profileService;

    /**
     * 지갑 소유자의 프로필 정보 생성 내지 기존 프로필 반환
     * @param walletAddress
     * @return
     */
    @PostMapping("/{walletAddress}")
    public ResponseEntity signUp(@PathVariable("walletAddress") String walletAddress) {
        Profile profile;
        try {
            profile = profileService.getProfile(walletAddress);
        } catch (IllegalStateException e) {
            profile = profileService.join(walletAddress);
        }

        return ResponseEntity.ok().body(profile);
    }

    @PatchMapping("/{walletAddress}")
    public ResponseEntity updateProfile(@PathVariable("walletAddress") String walletAddress,
                                        @Valid @RequestBody SimpleProfileInfoDto updatedProfileInfo) {
        Profile profile;
        try {
            profile = profileService.getProfile(walletAddress);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
        }
        Profile updatedProfile = profileService.updateProfile(profile, updatedProfileInfo);
        return ResponseEntity.ok().body(updatedProfile);
    }

    @PatchMapping("/{walletAddress}/nickname")
    public ResponseEntity updateNickname(@PathVariable("walletAddress") String walletAddress,
                                         @Valid @RequestBody SimpleProfileNicknameDto updatedProfileNickname) {
        Profile profile;
        try {
            profile = profileService.getProfile(walletAddress);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
        }
        Profile updatedProfile = profileService.updateNickname(profile, updatedProfileNickname.getNickname());
        return ResponseEntity.ok().body(updatedProfile);
    }

    @PatchMapping("/{walletAddress}/description")
    public ResponseEntity updateDescription(@PathVariable("walletAddress") String walletAddress,
                                            @Valid @RequestBody SimpleProfileDescDto updatedProfileDescDto) {
        Profile profile;
        try {
            profile = profileService.getProfile(walletAddress);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
        }
        Profile updatedProfile = profileService.updateDescription(profile, updatedProfileDescDto.getDescription());
        return ResponseEntity.ok().body(updatedProfile);
    }

    @PatchMapping("/{walletAddress}/image-uri")
    public ResponseEntity updateImageUri(@PathVariable("walletAddress") String walletAddress,
                                         @Valid @RequestBody SimpleProfileImageUriDto updatedProfileImageDto) {
        Profile profile;
        try {
            profile = profileService.getProfile(walletAddress);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
        }
        Profile updatedProfile = profileService.updateImageUri(profile, updatedProfileImageDto.getImageUri());
        return ResponseEntity.ok().body(updatedProfile);
    }

}
