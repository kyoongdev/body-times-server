package kyoongdev.body_times.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException {

  public CustomExceptionInterface customException;

}
