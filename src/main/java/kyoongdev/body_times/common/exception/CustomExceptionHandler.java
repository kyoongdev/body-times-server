package kyoongdev.body_times.common.exception;


import io.swagger.v3.oas.annotations.Hidden;
import java.sql.Timestamp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

  private String getUri() {
    String uri = ServletUriComponentsBuilder.fromCurrentRequest().toUriString();
    int i = uri.indexOf('/', 7);
    return uri.substring(i);
  }

  @ExceptionHandler(Exception.class)
  @Hidden

  public ResponseEntity<ErrorDTO> handleAllUncaughtException(Exception exception,
      WebRequest request) {
    log.error("Internal error occurred", exception);
    return new ResponseEntity<>(ErrorDTO.builder().status(HttpStatus.INTERNAL_SERVER_ERROR.value())
        .timestamp(new Timestamp(System.currentTimeMillis()).toString())
        .message("INTERNAL SERVER ERROR").path(getUri()).build(),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler({CustomException.class})
  protected ResponseEntity<ErrorDTO> handleCustomException(CustomException ex) {
    CustomExceptionInterface exception = ex.getCustomException();
    log.debug("exception :" + exception.getMessage());
    return new ResponseEntity<>(ErrorDTO.builder().status(exception.getStatusCode().value())
        .timestamp(new Timestamp(System.currentTimeMillis()).toString())
        .message(exception.getMessage()).path(getUri()).build(), exception.getStatusCode());
  }

  @ExceptionHandler(IllegalArgumentException.class)
  protected ResponseEntity<ErrorDTO> handleInvalidArgs(IllegalArgumentException ex) {
    return new ResponseEntity<>(
        ErrorDTO.builder().status(HttpStatus.BAD_REQUEST.value()).message("잘못된 요청입니다.")
            .timestamp(new Timestamp(System.currentTimeMillis()).toString()).path(getUri()).build(),
        HttpStatus.BAD_REQUEST);

  }


}
