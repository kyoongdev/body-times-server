package kyoongdev.body_times.utils.social;


import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import kyoongdev.body_times.utils.social.dto.KakaoCallback;
import kyoongdev.body_times.utils.social.dto.KakaoUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@Slf4j
public class KakaoSocial {


  @Value("${social.kakao.redirect-uri}")
  private String redirectUrl;

  @Value("${social.kakao.clientId}")
  private String clientId;
  @Value("${social.kakao.clientSecret}")
  private String clientSecret;


  public void getRest(HttpServletResponse response) throws IOException {

    String url = String.format(
        "https://kauth.kakao.com/oauth/authorize?client_id=%s&redirect_uri=%s&response_type=code",
        clientId,
        redirectUrl);

    response.sendRedirect(url);
  }

  public String getToken(String code) {
    WebClient webClient = WebClient.builder().baseUrl("https://kauth.kakao.com/oauth/token")
        .build();

    Map response = webClient.get().uri(
            uriBuilder -> uriBuilder.path("").queryParam("grant_type", "authorization_code")
                .queryParam("client_id", clientId).queryParam("client_secret", clientSecret)
                .queryParam("redirectUri", redirectUrl).queryParam("code", code).build()).retrieve()
        .bodyToMono(Map.class).block();
    log.info(response.toString());

    return (String) response.get("access_token");
  }

  public KakaoUser getUser(String token) {
    WebClient webClient = WebClient.builder().baseUrl("https://kapi.kakao.com/v2/user/me")
        .defaultHeaders(headers -> {
          headers.setBearerAuth(token);
          headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        }).build();

    Map response = webClient.get().retrieve().bodyToMono(Map.class).block();
    log.info("user: " + response.toString());
    String id = (String) response.get("id").toString();
    Map<String, Object> kakaoAccount = (Map<String, Object>) response.get("kakaoAccount");

    String email = Optional.ofNullable(kakaoAccount)
        .map(account -> (String) account.get("email"))
        .orElse(null);

    return KakaoUser.builder().id(id).email(email).build();
  }

  public KakaoCallback getRestCallback(String code) {
    String token = getToken(code);
    KakaoUser user = getUser(token);

    return KakaoCallback.builder().token(token).user(user).build();
  }


}
