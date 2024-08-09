package kyoongdev.body_times.modules.meal.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import kyoongdev.body_times.modules.food.entities.Food;
import kyoongdev.body_times.modules.meal.entities.Meal;
import kyoongdev.body_times.modules.user.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateMealDTO {

  @Schema(type = "string")
  String foodId;

  @Schema(type = "number")
  int year;

  @Schema(type = "number")
  int month;

  @Schema(type = "number")
  int day;

  @Schema(type = "number", nullable = true)
  int grams;


  public Meal toEntity(Food food, User user) {

    return Meal.builder().food(food).user(user).year(year).month(month).day(day).grams(grams)
        .build();

  }
}
