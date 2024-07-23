package kyoongdev.body_times.modules.user;


import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.UUID;
import kyoongdev.body_times.common.annotations.GetUser;
import kyoongdev.body_times.modules.user.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "유저")
@RequestMapping("/users")
@RestController
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;


  @GetMapping("me")
  @SecurityRequirement(name = "Bearer Authentication")
  @ApiResponses(
      @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = UserDTO.class)))
  )
  @PreAuthorize("hasAuthority('USER')")
  public UserDTO findMe(@GetUser CustomUserDetail user) {
    return userService.findUserById(UUID.fromString(user.getId()));
  }

  @GetMapping("{userId}/detail")
  public UserDTO findUser(@PathVariable("userId") String userId) {
    return userService.findUserById(UUID.fromString(userId));
  }

}
