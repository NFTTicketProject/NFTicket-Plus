package ssafy.nfticket.dto.block;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SimpleBlockHashTicketDto {

    @JsonProperty(value = "ticket_id")
    private Integer ticketId;

    @JsonProperty(value = "block_hash")
    private String blockHash;

    public SimpleBlockHashTicketDto(Integer ticketId, String blockHash) {
        this.ticketId = ticketId;
        this.blockHash = blockHash;
    }
}
