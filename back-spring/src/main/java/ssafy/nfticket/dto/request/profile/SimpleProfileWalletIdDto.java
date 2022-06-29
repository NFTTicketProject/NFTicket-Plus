package ssafy.nfticket.dto.request.profile;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class SimpleProfileWalletIdDto {
    @NotBlank()
    @JsonProperty(value = "wallet_id")
    private String walletId;

    public SimpleProfileWalletIdDto(String walletId) {
        this.walletId = walletId;
    }
}
