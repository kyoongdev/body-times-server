package kyoongdev.body_times.modules.user;


import java.util.Collection;
import java.util.Set;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
public class CustomUserDetail implements UserDetails {

  private String id;
  private Set<GrantedAuthority> authorities;

  CustomUserDetail(String id) {
    this.id = id;
  }

  @Override
  public String getUsername() {
    return this.id;
  }

  @Override
  public String getPassword() {
    return null;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.authorities;
  }

}
