package kyoongdev.body_times.modules.meal.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import kyoongdev.body_times.modules.food.dto.FoodDTO;
import kyoongdev.body_times.modules.meal.entities.Meal;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@Schema(description = "MealDTO")
public class MealDTO {

  @Schema(type = "string")
  private String id;

  @Schema()
  FoodDTO food;

  @Schema(type = "number")
  private int year;
  @Schema(type = "number")
  private int month;
  @Schema(type = "number")
  private int day;
  @Schema(type = "number", nullable = true)
  private int grams;


  public static MealDTO fromEntity(Meal meal) {
    return MealDTO.builder().id(meal.getId().toString()).food(FoodDTO.fromEntity(meal.getFood()))
        .year(meal.getYear()).day(meal.getDay()).month(meal.getMonth()).grams(meal.getGrams())
        .build();
  }


}
