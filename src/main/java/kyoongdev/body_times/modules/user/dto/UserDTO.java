package kyoongdev.body_times.modules.user.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import kyoongdev.body_times.modules.user.entities.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class UserDTO {


  @Schema(type = "string")
  private String id;
  @Schema(type = "string", nullable = true)
  private String nickname;

  @Schema(type = "number", nullable = false)
  private Double height;
  @Schema(type = "number", nullable = false)
  private Double weight;
  @Schema(type = "number", nullable = false)
  private Double maintainCalorie;
  @Schema(type = "number", nullable = false)
  private Double activityCalorie;


  public static UserDTO fromEntity(User user) {

    return UserDTO.builder().id(user.getId().toString()).nickname(user.getNickname())
        .height(user.getHeight()).weight(user.getWeight())
        .maintainCalorie(user.getMaintainCalorie()).activityCalorie(user.getActivityCalorie()
        ).build();

  }
}
