package ssafy.nfticket.common.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ssafy.nfticket.common.error.ErrorResponse;

import static ssafy.nfticket.common.error.ErrorCode.INVALID_INPUT_VALUE;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler{
    @ExceptionHandler(MethodArgumentNotValidException.class) // 유효성 검사 실패 시 발생하는 예외를 처리
    @ResponseBody
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        log.error("handle Method Argument Not Valid Exception : {}", INVALID_INPUT_VALUE);
        return ErrorResponse.toResponseEntity(INVALID_INPUT_VALUE);
    }

    @ExceptionHandler(value = {CustomException.class})
    protected ResponseEntity<ErrorResponse> handleCustomExceptoin(CustomException exception) {
        log.error("handle Custom Exception : {}", exception.getErrorCode());
        return ErrorResponse.toResponseEntity(exception.getErrorCode());
    }
}