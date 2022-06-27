package ssafy.nfticket.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ssafy.nfticket.dto.request.block.SimpleBlockHashDto;
import ssafy.nfticket.dto.request.block.SimpleBlockHashTicketDto;
import ssafy.nfticket.service.BlockService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/block")
public class BlockApiController {
    private final BlockService blockService;

    /**
     * 블록해시(티켓 아이디 + 블록해시) 저장
     */

    @PostMapping()
    public ResponseEntity<String> addBlockHash(@RequestBody SimpleBlockHashTicketDto simpleBlockHashTicketDto) {
        return ResponseEntity.ok().body(blockService.addBlockHash(simpleBlockHashTicketDto));
    }

    /**
     * 블록해시 반환
     */

    @GetMapping("/{ticketId}")
    public ResponseEntity<SimpleBlockHashDto> getBlockHash(@PathVariable("ticketId") Long ticketId) {
        return ResponseEntity.ok().body(new SimpleBlockHashDto(blockService.getBlockHash(ticketId)));
    }
}
