package ssafy.nfticket.dto.request.profile;

import lombok.Data;

@Data
public class SimpleProfileCreatedAtDto {

    private Integer createdAt;

    public SimpleProfileCreatedAtDto(Integer createdAt) {
        this.createdAt = createdAt;
    }
}
