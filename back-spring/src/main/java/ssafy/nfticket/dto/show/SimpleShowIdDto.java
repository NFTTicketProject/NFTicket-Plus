package ssafy.nfticket.dto.show;

import lombok.Data;

@Data
public class SimpleShowIdDto {

    private Long show_id;

    public SimpleShowIdDto(Long show_id) {
        this.show_id = show_id;
    }
}
