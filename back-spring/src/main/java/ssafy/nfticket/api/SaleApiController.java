package ssafy.nfticket.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ssafy.nfticket.dto.request.*;
import ssafy.nfticket.dto.response.SaleDescriptionResponseDto;
import ssafy.nfticket.dto.response.SaleShowScheduleIdResponseDto;
import ssafy.nfticket.dto.sale.SaleDescriptionDto;
import ssafy.nfticket.dto.profile.SimpleProfileDescDto;
import ssafy.nfticket.dto.sale.SaleDto;
import ssafy.nfticket.dto.response.SaleResponseDto;
import ssafy.nfticket.dto.sale.SaleShowScheduleIdDto;
import ssafy.nfticket.service.SaleService;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/sale")
@RequiredArgsConstructor
public class SaleApiController {
    private final SaleService saleService;

    //    @ApiOperation(value = "로그인", notes = "로그인시 jwt토큰 Bearer형식과 지갑 publickey 주소 응답")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "성공"),
//            @ApiResponse(code = 400, message = "잘못된 요청"),
//            @ApiResponse(code = 500, message = "서버 오류")
//    })
    @PostMapping()
    public ResponseEntity registerSale(@RequestBody @Valid SaleRequestDto saleRequestDto) throws Exception {

        try {
            long saleId = saleService.register(saleRequestDto);
            SaleResponseDto saleResponseDto = new SaleResponseDto();
            saleResponseDto.setSaleId(saleId);
            saleResponseDto.setMessage("Sale register success");
            return ResponseEntity.ok().body(saleResponseDto);

        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body("Sale register fail");
        }
    }

    @GetMapping()
    public ResponseEntity getSaleList() throws Exception {

        try {
            List<SaleDto> saleDtoList = saleService.getSaleList();
            return ResponseEntity.ok().body(saleDtoList);

        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body("Sale register fail");
        }
    }

    @PatchMapping("/{sale_id}")
    public ResponseEntity updateSale(@PathVariable("sale_id") long saleId,
                                     @Valid @RequestBody SaleRequestDto saleRequestDto) throws Exception {
        try {
            long updatedSaleId = saleService.updateSaleInfo(saleId, saleRequestDto);
            if (updatedSaleId == saleId)
                return ResponseEntity.ok().body(saleId);
            else
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("티켓 판매 정보 없음.");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("잘못된 요청입니다.");
        }
    }

    @GetMapping("/{sale_id}/show-schedule-id")
    public ResponseEntity getShowScheduleId(@PathVariable("sale_id") long saleId) throws Exception {

        try {
            SaleShowScheduleIdDto saleShowScheduleIdDto = saleService.getShowSchduleId(saleId);
            if (saleShowScheduleIdDto.getSale_id() == saleId) {
                SaleShowScheduleIdResponseDto saleShowScheduleIdResponseDto = new SaleShowScheduleIdResponseDto();
                saleShowScheduleIdResponseDto.setShow_schedule_id(saleShowScheduleIdDto.getShow_schedule_id());
                return ResponseEntity.ok().body(saleShowScheduleIdResponseDto);
            } else
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("티켓 판매 정보 없음.");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("잘못된 요청입니다.");
        }
    }

    @PatchMapping("/{sale_id}/show-schedule-id")
    public ResponseEntity updateShowScheduleId(@PathVariable("sale_id") long saleId,
                                               @Valid @RequestBody SaleShowScheduleIdRequestDto saleShowScheduleIdRequestDto) throws Exception {
        try {
            SaleShowScheduleIdDto saleShowScheduleIdDto = saleService.updateShowScheduleId(saleId, saleShowScheduleIdRequestDto);
            if (saleShowScheduleIdDto.getSale_id() == saleId)
                return ResponseEntity.ok().body(saleShowScheduleIdDto.getShow_schedule_id());
            else
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("티켓 판매 정보 없음.");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("잘못된 요청입니다.");
        }
    }

    @GetMapping("/{sale_id}/description")
    public ResponseEntity getDescription(@PathVariable("sale_id") long saleId) throws Exception {

        try {
            SaleDescriptionDto saleDescriptionDto = saleService.getDescription(saleId);
            if (saleDescriptionDto.getSaleId() == saleId) {
                SaleDescriptionResponseDto saleDescriptionResponseDto = new SaleDescriptionResponseDto();
                saleDescriptionResponseDto.setDescription(saleDescriptionDto.getDescription());
                return ResponseEntity.ok().body(saleDescriptionResponseDto);
            } else
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("티켓 판매 정보 없음.");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("잘못된 요청입니다.");
        }
    }

    @PatchMapping("/{sale_id}/description")
    public ResponseEntity updateDescription(@PathVariable("sale_id") long saleId,
                                            @Valid @RequestBody SaleDescriptionRequestDto saleDescriptionRequestDto) throws Exception {
        try {
            SaleDescriptionDto saleDescriptionDto = saleService.updateDescription(saleId, saleDescriptionRequestDto);
            if (saleDescriptionDto.getSaleId() == saleId)
                return ResponseEntity.ok().body(saleDescriptionDto.getDescription());
            else
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("티켓 판매 정보 없음.");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("잘못된 요청입니다.");
        }
    }

    @GetMapping("/{sale_id}/started-at")
    public ResponseEntity getStartedAt(@PathVariable("sale_id") long saleId) throws Exception {
        return null;
    }

    @PatchMapping("/{sale_id}/started-at")
    public ResponseEntity updateStartedAt(@PathVariable("sale_id") long saleId,
                                          @Valid @RequestBody SaleStartedAtRequestDto saleDescriptionRequestDto) throws Exception {
        return null;
    }

    @GetMapping("/{sale_id}/ended-at")
    public ResponseEntity getEndedAt(@PathVariable("sale_id") long saleId) throws Exception {
        return null;
    }

    @PatchMapping("/{sale_id}/ended-at")
    public ResponseEntity updateEndedAt(@PathVariable("sale_id") long saleId,
                                        @Valid @RequestBody SaleEndedAtRequestDto saleDescriptionRequestDto) throws Exception {
        return null;
    }


}
