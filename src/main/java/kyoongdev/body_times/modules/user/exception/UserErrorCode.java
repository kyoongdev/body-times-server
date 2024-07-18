package kyoongdev.body_times.modules.user.exception;


import kyoongdev.body_times.common.exception.CustomExceptionInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum UserErrorCode implements CustomExceptionInterface {

  NOT_FOUND(HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다.");

  private final HttpStatus statusCode;
  private final String message;
}
