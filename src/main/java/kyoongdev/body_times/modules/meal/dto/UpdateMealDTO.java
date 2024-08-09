package kyoongdev.body_times.modules.meal.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import kyoongdev.body_times.common.dto.BaseUpdateDTO;
import kyoongdev.body_times.modules.meal.entities.Meal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class UpdateMealDTO extends BaseUpdateDTO {

  @Schema(type = "number", nullable = true)
  int year;

  @Schema(type = "number", nullable = true)
  int month;

  @Schema(type = "number", nullable = true)
  int day;

  @Schema(type = "number", nullable = true)
  int grams;


  public Meal toEntity(Meal meal) {
    myCopyProperties(this, meal);
    return meal;
  }


}
