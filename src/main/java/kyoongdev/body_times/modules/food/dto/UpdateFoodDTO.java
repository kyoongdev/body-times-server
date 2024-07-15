package kyoongdev.body_times.modules.food.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import kyoongdev.body_times.modules.food.entities.Food;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateFoodDTO {


  @Schema(description = "음식 이름", type = "string")
  private String name;

  @Schema(description = "탄수량", type = "number")
  private Double carbo;

  @Schema(description = "단백질", type = "number")
  private Double protein;

  @Schema(description = "지방량", type = "number")
  private Double fat;

  @Schema(description = "칼로리", type = "number")
  private Double calorie;


  public Food toEntity() {
    return Food.builder()
        .name(name)
        .fat(fat)
        .carbo(carbo)
        .protein(protein)
        .calorie(calorie)
        .build();
  }

}
