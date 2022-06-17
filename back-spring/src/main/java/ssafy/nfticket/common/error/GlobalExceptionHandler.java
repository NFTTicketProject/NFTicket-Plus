package ssafy.nfticket.common.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ssafy.nfticket.common.error.ErrorResponse;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler{
    @ExceptionHandler(MethodArgumentNotValidException.class) // 유효성 검사 실패 시 발생하는 예외를 처리
    @ResponseBody
    protected ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        return ErrorResponse.toResponseEntity(ErrorCode.INVALID_INPUT_VALUE);
    }
}