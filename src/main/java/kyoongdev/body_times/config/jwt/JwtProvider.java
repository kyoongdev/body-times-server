package kyoongdev.body_times.config.jwt;


import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import kyoongdev.body_times.modules.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtProvider {

  private final UserService userService;

  @Value("${jwt.secret}")
  private String secret;

  @Value("${jwt.access_token_expires_in}")
  private long accessTokenExpiresIn;
  @Value("${jwt.refresh_token_expires_in}")
  private long refreshTokenExpiresIn;


  public String generateToken(String id, long expiresIn) {

    Date date = new Date();

    return Jwts.builder()
        .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
        .setExpiration(new Date(date.getTime() + expiresIn)).claim("id", id).signWith(
            SignatureAlgorithm.HS256, secret).compact();
  }

}
