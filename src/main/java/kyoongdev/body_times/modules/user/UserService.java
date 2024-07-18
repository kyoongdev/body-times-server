package kyoongdev.body_times.modules.user;


import java.util.UUID;
import kyoongdev.body_times.common.exception.CustomException;
import kyoongdev.body_times.modules.user.dto.UserDTO;
import kyoongdev.body_times.modules.user.entities.User;
import kyoongdev.body_times.modules.user.exception.UserErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;


  public UserDTO findUserById(UUID id) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new CustomException(UserErrorCode.NOT_FOUND));

    return UserDTO.fromEntity(user);
  }

}
