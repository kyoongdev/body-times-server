package kyoongdev.body_times.common.exception;


import java.sql.Timestamp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestControllerAdvice
public class CustomExceptionHandler {

  private String getUri() {
    String uri = ServletUriComponentsBuilder.fromCurrentRequest().toUriString();
    int i = uri.indexOf('/', 7);
    return uri.substring(i);
  }


  @ExceptionHandler({CustomException.class})
  protected ResponseEntity handleCustomException(CustomException ex) {
    CustomExceptionInterface exception = ex.getCustomException();
    return new ResponseEntity(ErrorDTO.builder().status(exception.getStatusCode().value())
        .timestamp(new Timestamp(System.currentTimeMillis()).toString())
        .message(exception.getMessage()).path(getUri()).build(), exception.getStatusCode());
  }

  @ExceptionHandler({IllegalArgumentException.class})
  protected ResponseEntity handleInvalidArgs(IllegalArgumentException ex) {
    return new ResponseEntity(
        ErrorDTO.builder().status(HttpStatus.BAD_REQUEST.value()).message("잘못된 요청입니다.")
            .timestamp(new Timestamp(System.currentTimeMillis()).toString()).path(getUri()).build(),
        HttpStatus.BAD_REQUEST);

  }


}
