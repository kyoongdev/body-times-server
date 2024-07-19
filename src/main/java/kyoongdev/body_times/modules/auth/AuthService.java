package kyoongdev.body_times.modules.auth;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import kyoongdev.body_times.config.jwt.JwtProvider;
import kyoongdev.body_times.modules.auth.dto.TokenDTO;
import kyoongdev.body_times.modules.user.UserRepository;
import kyoongdev.body_times.modules.user.dto.CreateSocialUserDTO;
import kyoongdev.body_times.modules.user.entities.User;
import kyoongdev.body_times.utils.social.KakaoSocial;
import kyoongdev.body_times.utils.social.dto.KakaoUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {

  private final KakaoSocial kakaoSocial;

  private final UserRepository userRepository;

  private final JwtProvider jwtProvider;

  @Value("${client_url}")
  private String clientUrl;


  public void kakaoLogin(HttpServletResponse response) throws IOException {
    kakaoSocial.getRest(response);
  }

  public void kakaoCallback(String code, HttpServletResponse response) throws IOException {
    KakaoUser user = kakaoSocial.getRestCallback(code).getUser();

    User newUser = userRepository.save(CreateSocialUserDTO.toEntity(user));

    TokenDTO token = jwtProvider.createToken(
        jwtProvider.getAuthentication(newUser.getId().toString()));

    String redirectUri = UriComponentsBuilder.fromUriString(clientUrl)
        .queryParam("accessToken", token.getAccessToken())
        .queryParam("refreshToken", token.getRefreshToken())
        .build().toUriString();
    
    response.sendRedirect(redirectUri);

  }


}
