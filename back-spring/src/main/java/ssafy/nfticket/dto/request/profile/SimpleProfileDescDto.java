package ssafy.nfticket.dto.request.profile;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class SimpleProfileDescDto {
    @NotBlank()
    private String description;

    public SimpleProfileDescDto(String description) {
        this.description = description;
    }
}
