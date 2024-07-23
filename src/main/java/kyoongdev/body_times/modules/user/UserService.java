package kyoongdev.body_times.modules.user;


import java.util.Optional;
import java.util.UUID;
import kyoongdev.body_times.common.exception.CustomException;
import kyoongdev.body_times.modules.user.dto.UserDTO;
import kyoongdev.body_times.modules.user.entities.User;
import kyoongdev.body_times.modules.user.exception.UserErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;


  public UserDTO findUserById(UUID id) {
    Optional<User> user = userRepository.findById(id);

    if (user.isEmpty()) {
      throw new CustomException(UserErrorCode.NOT_FOUND);
    }

    return UserDTO.fromEntity(user.get());
  }

}
