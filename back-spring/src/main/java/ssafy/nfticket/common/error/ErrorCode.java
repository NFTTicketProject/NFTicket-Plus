package ssafy.nfticket.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    /* 400 BAD_REQUEST : 잘못된 요청 */
    INVALID_INPUT_VALUE(BAD_REQUEST, "유효하지 않은 정보를 입력했습니다."),

    /* 401 UNAUTHORIZED : 인증되지 않은 사용자 */

    /* 403 FORBIDDEN : 권한이 없는 사용자 */

    /* 404 NOT FOUND : resource를 찾을 수 없음 */
    DATA_NOT_FOUND(NOT_FOUND, "해당 정보를 찾을 수 없습니다.")

    /* 409 CONFLICT : resource의 현재 상태와 충돌 */
    ;

    private final String message;
    private final HttpStatus status;

    ErrorCode(final HttpStatus status, final String message) {
        this.status = status;
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public HttpStatus getStatus() {
        return this.status;
    }
}
