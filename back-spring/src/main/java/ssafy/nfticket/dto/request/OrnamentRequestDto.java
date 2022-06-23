package ssafy.nfticket.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;

@Data
@Getter
@ToString
public class OrnamentRequestDto {

    @NotBlank(message = "유효하지 않은 타입입니다")
    String exhibitType;
    float angle;
    float xpos;
    float ypos;
    float zpos;
    @URL(message ="URL 값이 아닙니다.")
    String ipfsURL;
}
