package ssafy.nfticket.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ssafy.nfticket.entity.Profile;
import ssafy.nfticket.service.ProfileService;
import org.springframework.http.ResponseEntity;

@RestController
@RequiredArgsConstructor
public class ProfileApiController {

    private final ProfileService profileService;

    @PostMapping("/account/{walletAddress}")
    public ResponseEntity signUp(@PathVariable("walletAddress") String walletAddress) {

        Profile newProfile = profileService.join(walletAddress);

        return ResponseEntity.ok().body(newProfile);
    }
}
