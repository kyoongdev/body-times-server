package kyoongdev.body_times.modules.food.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateFoodDTO {

  @NotBlank(message = "음식 이름을 입력해주세요")
  @Schema(description = "음식 이름",type = "string")
  private String name;
  @NotBlank(message = "탄수량을 입력해주세요")
  @Schema(description = "탄수량",type = "number")
  private Double carbo;
  @NotBlank(message = "단백질량을 입력해주세요")
  @Schema(description = "단백질",type = "number")
  private Double protein;
  @NotBlank(message = "지방량을 입력해주세요")
  @Schema(description = "지방량",type = "number")
  private Double fat;
  @NotBlank(message = "칼로리를 입력해주세요")
  @Schema(description = "칼로리",type = "number")
  private Double calorie;

}
