package kyoongdev.body_times.modules.food.entities;


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

@Entity(name= "food")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Food {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(length = 200, nullable = false)
  private String name;

  @Column(nullable = false)
  private Double carbo;

  @Column(nullable = false)
  private Double protein;

  @Column(nullable = false)
  private Double fat;
  @Column(nullable = false)
  private Double calorie;

}
