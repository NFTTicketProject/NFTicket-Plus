package ssafy.nfticket.dto.request.ticket;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SimpleBlockHashTicketDto {

    @JsonProperty(value = "ticket_id")
    private Long ticketId;

    @JsonProperty(value = "block_hash")
    private String blockHash;

    public SimpleBlockHashTicketDto(Long ticketId, String blockHash) {
        this.ticketId = ticketId;
        this.blockHash = blockHash;
    }
}
