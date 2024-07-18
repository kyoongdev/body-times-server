package kyoongdev.body_times.modules.auth;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import kyoongdev.body_times.modules.user.UserRepository;
import kyoongdev.body_times.modules.user.dto.CreateSocialUserDTO;
import kyoongdev.body_times.utils.social.KakaoSocial;
import kyoongdev.body_times.utils.social.dto.KakaoUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class AuthService {

  private final KakaoSocial kakaoSocial;

  private final UserRepository userRepository;


  public void kakaoLogin(HttpServletResponse response) throws IOException {
    kakaoSocial.getRest(response);
  }

  public void kakaoCallback(String code, HttpServletResponse response) throws IOException {
    KakaoUser user = kakaoSocial.getRestCallback(code).getUser();

    userRepository.save(CreateSocialUserDTO.toEntity(user));

    response.sendRedirect("http://localhost:3000");

  }


}
