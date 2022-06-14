package ssafy.nfticket.api;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ssafy.nfticket.dto.profile.*;
import ssafy.nfticket.dto.response.ErrorResponse;
import ssafy.nfticket.dto.simple.SimpleProfileDto;
import ssafy.nfticket.entity.Profile;
import ssafy.nfticket.service.ProfileService;
import org.springframework.http.ResponseEntity;

@RestController
@RequiredArgsConstructor
public class ProfileApiController {

    private final ProfileService profileService;

    private final MessageSource messageSource;

    /**
     * 지갑 소유자의 프로필 정보 생성 내지 기존 프로필 반환
     * @param walletAddress
     * @return
     */
    @PostMapping("/account/{walletAddress}")
    public ResponseEntity signUp(@PathVariable("walletAddress") String walletAddress) {

        Profile newProfile = profileService.join(walletAddress);

        return ResponseEntity.ok().body(newProfile);
    }

    /**
     * 지갑 소유자의 프로필 조회 (타인)
     * @param walletAddress
     * @return
     */
    @GetMapping("/profile/{walletAddress}")
    public ResponseEntity getProfile(@PathVariable("walletAddress") String walletAddress) {
        try {
            Profile newProfile = profileService.getProfile(walletAddress);
            return ResponseEntity.ok().body(newProfile);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body("프로필 없음");
        }
    }

    /**
     * 지갑 소유자의 닉네임 조회 (타인)
     * @param walletAddress
     * @return
     */
    @GetMapping("/profile/{walletAddress}/nickname")
    public ResponseEntity getProfileNickname(@PathVariable("walletAddress") String walletAddress) {
        try {
            Profile newProfile = profileService.getProfile(walletAddress);
            return ResponseEntity.ok().body(new SimpleProfileNicknameDto(newProfile.getNickname()));
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body("프로필 없음");
        }
    }

    /**
     * 지갑 소유자의 설명 조회 (타인)
     * @param walletAddress
     * @return
     */
    @GetMapping("/profile/{walletAddress}/description")
    public ResponseEntity getProfileDescription(@PathVariable("walletAddress") String walletAddress) {
        try {
            Profile newProfile = profileService.getProfile(walletAddress);
            return ResponseEntity.ok().body(new SimpleProfileDescDto(newProfile.getDescription()));
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body("프로필 없음");
        }
    }

    /**
     * 지갑 소유자의 프로필 이미지 조회 (타인)
     * @param walletAddress
     * @return
     */
    @GetMapping("/profile/{walletAddress}/image-uri")
    public ResponseEntity getProfileImage(@PathVariable("walletAddress") String walletAddress) {
        try {
            Profile newProfile = profileService.getProfile(walletAddress);
            return ResponseEntity.ok().body(new SimpleProfileImageUriDto(newProfile.getImageUri()));
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body("프로필 없음");
        }
    }

    /**
     * 지갑 소유자의 가입 시점 조회 (타인)
     * @param walletAddress
     * @return
     */
    @GetMapping("/profile/{walletAddress}/created-at")
    public ResponseEntity getProfileCreatedAt(@PathVariable("walletAddress") String walletAddress) {
        try {
            Profile newProfile = profileService.getProfile(walletAddress);
            return ResponseEntity.ok().body(new SimpleProfileCreatedAtDto(newProfile.getCreatedAt()));
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body("프로필 없음");
        }
    }

    /**
     * 지갑 소유자의 갤러리 타입 조회 (타인)
     * @param walletAddress
     * @return
     */
    @GetMapping("/profile/{walletAddress}/gallery")
    public ResponseEntity getProfileGallery(@PathVariable("walletAddress") String walletAddress) {
        try {
            Profile newProfile = profileService.getProfile(walletAddress);
            return ResponseEntity.ok().body(new SimpleProfileGalleryDto(newProfile.getGallery()));
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body("프로필 없음");
        }
    }




}
