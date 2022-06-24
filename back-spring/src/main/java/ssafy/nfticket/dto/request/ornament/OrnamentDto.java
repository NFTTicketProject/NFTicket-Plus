package ssafy.nfticket.dto.request.ornament;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrnamentDto {
    @NotNull
    String walletAddress;
    String exhibitType;
    float xPos;
    float yPos;
    float zPos;
    String ipfsURL;
}
