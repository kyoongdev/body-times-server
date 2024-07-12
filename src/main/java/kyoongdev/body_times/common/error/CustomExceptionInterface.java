package kyoongdev.body_times.common.error;

import org.springframework.http.HttpStatus;

public interface CustomExceptionInterface {
  public HttpStatus getStatusCode();
  public String getMessage();
  

}
