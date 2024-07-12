package kyoongdev.body_times.common.exception;

import org.springframework.http.HttpStatus;

public interface CustomExceptionInterface {
  public HttpStatus getStatusCode();
  public String getMessage();
  

}
