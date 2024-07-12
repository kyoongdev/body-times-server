package kyoongdev.body_times.common.error;


import java.sql.Timestamp;
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
    return new ResponseEntity(ErrorDTO.builder().status(exception.getStatusCode())
        .timestamp(new Timestamp(System.currentTimeMillis()).toString())
        .message(exception.getMessage()).path(getUri()).build(), exception.getStatusCode());
  }


}
