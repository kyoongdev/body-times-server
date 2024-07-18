package kyoongdev.body_times.modules.user.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  @Column(nullable = true)
  private String nickname;
  @Column(unique = true)
  private String socialId;
  @Column(nullable = true)
  private Double height;
  @Column(nullable = true)
  private Double weight;
  @Column(nullable = true)
  private Double maintainCalorie;
  @Column(nullable = true)
  private Double activityCalorie;
}
