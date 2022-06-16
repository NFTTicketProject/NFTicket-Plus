package ssafy.nfticket.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ssafy.nfticket.dto.profile.SimpleProfileDescDto;
import ssafy.nfticket.dto.sale.SaleDto;
import ssafy.nfticket.entity.Profile;
import ssafy.nfticket.request.SaleRequestDto;
import ssafy.nfticket.response.SaleResponseDto;
import ssafy.nfticket.service.SaleService;

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
    public ResponseEntity registerSale(@RequestBody SaleRequestDto saleRequestDto) throws Exception {

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

    @PostMapping()
    public ResponseEntity getSaleList() throws Exception {

        try {
            List<SaleDto> saleDtoList = saleService.getSaleList();
            return ResponseEntity.ok().body(saleDtoList);

        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body("Sale register fail");
        }
    }
}
