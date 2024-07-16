package kyoongdev.body_times.utils.social.dto;


import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class KakaoUser {

  private String id;
  private String email;

}
