package kyoongdev.body_times.modules.meal.exception;


import kyoongdev.body_times.common.exception.CustomExceptionInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum MealErrorCode implements CustomExceptionInterface {

  NOT_FOUND(HttpStatus.NOT_FOUND, "식사를 찾을 수 없습니다.");

  private final HttpStatus statusCode;
  private final String message;

}
