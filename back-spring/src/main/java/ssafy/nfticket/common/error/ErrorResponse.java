package ssafy.nfticket.common.error;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
@Builder
public class ErrorResponse {

    private final int status;
    private final String message;
    private final String code;
    private final String error;

    public static ResponseEntity<ErrorResponse> toResponseEntity(ErrorCode errorCode) {
        return ResponseEntity
                .status(errorCode.getStatus())
                .body(ErrorResponse.builder()
                        .status(errorCode.getStatus().value()) // 400
                        .error(errorCode.getStatus().name())  // BAD_REQUEST
                        .code(errorCode.name())  // INVALID_INPUT_VALUE
                        .message(errorCode.getMessage()) // 유효하지 않은 정보를 입력했습니다.
                        .build());
    }
}
