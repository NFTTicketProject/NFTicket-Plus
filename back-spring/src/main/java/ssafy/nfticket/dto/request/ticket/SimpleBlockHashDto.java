package ssafy.nfticket.dto.request.ticket;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SimpleBlockHashDto {
    @JsonProperty(value = "block_hash")
    private String blockHash;

    public SimpleBlockHashDto(String blockHash) { this.blockHash = blockHash; }
}
