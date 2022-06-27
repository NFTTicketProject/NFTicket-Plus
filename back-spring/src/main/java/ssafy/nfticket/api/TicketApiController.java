package ssafy.nfticket.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ssafy.nfticket.dto.request.sale.SaleRequestDto;
import ssafy.nfticket.dto.request.ticket.SimpleTicketDto;
import ssafy.nfticket.dto.response.ShowResponseDto;
import ssafy.nfticket.dto.response.sale.SaleResponseDto;
import ssafy.nfticket.entity.Profile;
import ssafy.nfticket.entity.Ticket;
import ssafy.nfticket.service.ProfileService;
import ssafy.nfticket.service.TicketService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 티켓 정보 컨트롤러
 */
@RestController
@RequiredArgsConstructor
public class TicketApiController {

    private final TicketService ticketService;
    private final ProfileService profileService;

    /**
     * 해당 유저 이름으로 티켓 저장
     * @param simpleTicketDto
     * @param walletAddress
     * @return
     * @throws Exception
     */
    @PostMapping("/ticket/{walletAddress}")
    public ResponseEntity addTicket(@RequestBody @Valid SimpleTicketDto simpleTicketDto,
                                       @PathVariable("walletAddress") String walletAddress) throws Exception {

        try {
            Profile profile = profileService.getProfile(walletAddress);
            long saleId = ticketService.addTicket(simpleTicketDto, profile);
            return ResponseEntity.ok().body(saleId);

        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body("티켓 백 DB 저장 실패");
        }
    }

    @GetMapping("/ticket/{walletAddress}")
    public ResponseEntity getTickets(@PathVariable("walletAddress") String walletAddress) throws Exception {

        try {
            List<Ticket> tickets = ticketService.getTickets(walletAddress);
            List<SimpleTicketDto> ticketDtos = tickets.stream().map(x -> new SimpleTicketDto(x)).collect(Collectors.toList());

            return ResponseEntity.ok().body(ticketDtos);

        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body("티켓 백 DB 저장 실패");
        }
    }




}
