package kyoongdev.body_times.modules.food.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import kyoongdev.body_times.modules.food.entities.Food;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
@Schema(description = "FoodDTO")
public class FoodDTO {

  @Schema(type = "string")
  private String id;

  @Schema(type = "string")
  private String name;

  @Schema(type = "number")
  private Double carbo;
  @Schema(type = "number")
  private Double protien;
  @Schema(type = "number")
  private Double fat;
  @Schema(type = "number")
  private Double calorie;


  public static FoodDTO fromEntity(Food food) {
    return FoodDTO.builder().id(food.getId().toString()).name(food.getName()).carbo(food.getCarbo())
        .protien(food.getProtein()).fat(food.getFat()).calorie(food.getCalorie()).build();
  }

}
