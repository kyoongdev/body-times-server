package kyoongdev.body_times.common.error;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
@Builder
public class ErrorDTO {
  private final HttpStatus status;
  private final String timestamp;
  private final String path;
  private final String message;
}