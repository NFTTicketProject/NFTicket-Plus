package ssafy.nfticket.dto.request.ticket;

import lombok.Data;
import ssafy.nfticket.entity.Ticket;

@Data
public class SimpleTicketDto {

    private Integer saleId;
    private String imageUri;
    private String blockHash;

    public SimpleTicketDto(Ticket ticket) {
        this.saleId = ticket.getSaleId();
        this.imageUri = ticket.getImageUri();
        this.blockHash = ticket.getBlockHash();
    }
}
