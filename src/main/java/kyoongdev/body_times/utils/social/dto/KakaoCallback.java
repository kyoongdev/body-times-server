package kyoongdev.body_times.utils.social.dto;


import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class KakaoCallback {

  private String token;

  private KakaoUser user;

}
