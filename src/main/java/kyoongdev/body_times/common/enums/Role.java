package kyoongdev.body_times.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {
  USER(ROLES.USER);

  public static class ROLES {

    public static final String USER = "USER";
    public static final String ADMIN = "ADMIN";
  }


  private final String role;
}