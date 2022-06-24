package ssafy.nfticket.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ssafy.nfticket.dto.request.ornament.OrnamentDto;
import ssafy.nfticket.dto.request.ornament.OrnamentRequestDto;
import ssafy.nfticket.service.OrnamentService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ornament")
public class OrnamentApiController {

    private final OrnamentService ornamentService;

    @PostMapping("/{walletAddress}")
    public ResponseEntity registerOrnament(@PathVariable @NotNull String walletAddress,
                                           @RequestBody @Valid OrnamentRequestDto ornamentRequestDto) throws Exception {
        System.out.println(ornamentRequestDto.toString());
        try {
            if (ornamentService.register(walletAddress, ornamentRequestDto).equals(walletAddress)) {
                return ResponseEntity.ok().body("Ornament register Success");
            }else
                return ResponseEntity.status(HttpStatus.NO_CONTENT)
                        .body("Ornament register fail");

        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("잘못된 요청입니다.");
        }
    }

    @GetMapping("/{walletAddress}")
    public ResponseEntity getOrnamentList(@PathVariable String walletAddress) throws Exception {
        try {
            List<OrnamentDto> ornamentDtoList = ornamentService.getOrnamentList(walletAddress);
            if (!ornamentDtoList.isEmpty() && ornamentDtoList.get(0).getWalletAddress().equals(walletAddress)) {
                return ResponseEntity.ok().body(ornamentDtoList);
            } else
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("장식품 정보 없음.");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("잘못된 요청입니다.");
        }
    }
}

