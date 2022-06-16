package ssafy.nfticket.dto.profile;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SimpleProfileDescDto {
    @NotBlank(message = "유효하지 않은 소개입니다.")
    private String description;

    public SimpleProfileDescDto(String description) {
        this.description = description;
    }
}
