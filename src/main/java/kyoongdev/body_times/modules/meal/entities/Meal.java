package kyoongdev.body_times.modules.meal.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.util.UUID;
import kyoongdev.body_times.modules.food.entities.Food;
import kyoongdev.body_times.modules.user.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name= "meal")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Meal {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @ManyToOne(fetch = FetchType.LAZY)
  private Food food;


  @ManyToOne(fetch = FetchType.LAZY)
  private User user;
  @Column(nullable = false)
  private int year;
  @Column(nullable = false)
  private int month;
  @Column(nullable = false)
  private int day;

  @Column(nullable = true)
  private int grams;



}
