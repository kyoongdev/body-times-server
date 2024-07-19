package kyoongdev.body_times.modules.user;


import java.util.UUID;
import kyoongdev.body_times.modules.user.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

  private final UserService userService;

  @Override
  public CustomUserDetail loadUserByUsername(String id) {
    UserDTO user = userService.findUserById(UUID.fromString(id));

    return new CustomUserDetail(user.getId());
  }

}
