package kyoongdev.body_times.common.exception;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class ErrorDTO {

  private final int status;
  private final String timestamp;
  private final String path;
  private final String message;
}