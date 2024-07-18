package kyoongdev.body_times.modules.user.dto;

import kyoongdev.body_times.modules.user.entities.User;
import kyoongdev.body_times.utils.social.dto.KakaoUser;

public class CreateSocialUserDTO {


  public static User toEntity(KakaoUser user) {
    return User.builder().socialId(user.getId()).build();
  }


}
