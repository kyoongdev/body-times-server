package kyoongdev.body_times.modules.auth;


import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import kyoongdev.body_times.utils.social.KakaoSocial;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "인증")
@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
public class AuthController {

  private final KakaoSocial kakaoSocial;

  @GetMapping("social/kakao/callback")
  public void kakaoCallback() {
  }

  @GetMapping("social/kakao")
  public void kakaoLogin(HttpServletResponse response) throws IOException {
    kakaoSocial.getRest(response);
  }


}
