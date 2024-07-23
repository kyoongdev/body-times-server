package kyoongdev.body_times.modules.user;


import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import kyoongdev.body_times.modules.user.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailService implements UserDetailsService {

  private final UserService userService;

  @Override
  public CustomUserDetail loadUserByUsername(String id) {
    UserDTO user = userService.findUserById(UUID.fromString(id));

    Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
    grantedAuthorities.add((GrantedAuthority) () -> "USER");

    return new CustomUserDetail(user, grantedAuthorities);
  }

}
