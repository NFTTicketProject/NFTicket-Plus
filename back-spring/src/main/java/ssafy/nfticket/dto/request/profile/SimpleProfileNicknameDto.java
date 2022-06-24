package ssafy.nfticket.dto.request.profile;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class SimpleProfileNicknameDto {
    @NotBlank()
    private String nickname;

    public SimpleProfileNicknameDto(String nickname) {
        this.nickname = nickname;
    }
}
