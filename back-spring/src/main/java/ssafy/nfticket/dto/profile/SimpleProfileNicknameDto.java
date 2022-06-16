package ssafy.nfticket.dto.profile;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class SimpleProfileNicknameDto {
    @NotBlank(message = "유효하지 않은 닉네임입니다.")
    private String nickname;

    public SimpleProfileNicknameDto(String nickname) {
        this.nickname = nickname;
    }
}
