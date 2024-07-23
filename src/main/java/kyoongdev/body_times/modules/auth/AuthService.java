package kyoongdev.body_times.modules.auth;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import kyoongdev.body_times.modules.auth.dto.TokenDTO;
import kyoongdev.body_times.modules.user.UserRepository;
import kyoongdev.body_times.modules.user.dto.CreateSocialUserDTO;
import kyoongdev.body_times.modules.user.entities.User;
import kyoongdev.body_times.utils.jwt.JwtProvider;
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

    Optional<User> isExists = userRepository.findUserBySocialId(user.getId());
    TokenDTO token;
    if (isExists.isPresent()) {
      token = jwtProvider.createToken(
          jwtProvider.getAuthentication(isExists.get().getId().toString()));
    } else {
      User newUser = userRepository.save(CreateSocialUserDTO.toEntity(user));
      token = jwtProvider.createToken(
          jwtProvider.getAuthentication(newUser.getId().toString()));
    }

    String redirectUri = UriComponentsBuilder.fromUriString(clientUrl)
        .replaceQueryParam("accessToken", token.getAccessToken())
        .replaceQueryParam("refreshToken", token.getRefreshToken())
        .build().toUriString();

    log.info(redirectUri);

    response.sendRedirect(redirectUri);

  }

}
