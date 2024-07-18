package kyoongdev.body_times.modules.auth.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class TokenDTO {

  @Schema(type = "string")
  private String accessToken;

  @Schema(type = "string")
  private String refreshToken;

}
