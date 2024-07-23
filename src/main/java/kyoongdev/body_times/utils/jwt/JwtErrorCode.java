package kyoongdev.body_times.utils.jwt;

import kyoongdev.body_times.common.exception.CustomExceptionInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum JwtErrorCode implements CustomExceptionInterface {

  EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "만료된 토큰입니다."),
  INVALID_TOKEN(HttpStatus.BAD_REQUEST, "잘못된 토큰입니다.");

  private final HttpStatus statusCode;
  private final String message;

}
