package kyoongdev.body_times.config.jwt;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Optional;
import kyoongdev.body_times.common.exception.CustomException;
import kyoongdev.body_times.modules.auth.dto.TokenDTO;
import kyoongdev.body_times.modules.user.CustomUserDetail;
import kyoongdev.body_times.modules.user.CustomUserDetailService;
import kyoongdev.body_times.modules.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class JwtProvider {

  private final UserService userService;
  private final CustomUserDetailService customUserDetailService;


  @Value("${jwt.secret}")
  private String secret;

  @Value("${jwt.access_token_expires_in}")
  private long accessTokenExpiresIn;
  @Value("${jwt.refresh_token_expires_in}")
  private long refreshTokenExpiresIn;


  @Transactional
  public TokenDTO createToken(Authentication authentication) {
    String id = authentication.getName();

    String accessToken = generateToken(id, accessTokenExpiresIn);
    String refreshToken = generateToken(id, refreshTokenExpiresIn);

    return TokenDTO.builder().accessToken(accessToken).refreshToken(refreshToken).build();
  }


  public String generateToken(String id, long expiresIn) {

    Date date = new Date();

    return Jwts.builder()
        .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
        .setExpiration(new Date(date.getTime() + expiresIn)).claim("id", id).signWith(
            SignatureAlgorithm.HS256, secret).compact();
  }

  public Claims validateToken(String token) {
    try {
      return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    } catch (ExpiredJwtException e) {
      throw new CustomException(JwtErrorCode.EXPIRED_TOKEN);
    } catch (JwtException e) {
      throw new CustomException(JwtErrorCode.INVALID_TOKEN);
    }
  }


  public Optional<String> resolveToken(HttpServletRequest req) throws JwtException {
    String bearerToken = req.getHeader("Authorization");
    if (bearerToken != null) {
      return Optional.of(bearerToken.substring(7));
    }
    return Optional.empty();
  }


  public String compareToken(TokenDTO token) {
    String accessTokenSubject = validateToken(token.getAccessToken()).getSubject();
    String refreshTokenSubject = validateToken(token.getRefreshToken()).getSubject();

    if (!accessTokenSubject.equals(refreshTokenSubject)) {
      throw new CustomException(JwtErrorCode.INVALID_TOKEN);
    }
    return refreshTokenSubject;
  }


  public Authentication getAuthentication(String subject) {
    CustomUserDetail userDetails = customUserDetailService.loadUserByUsername(subject);
    return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
  }

}
