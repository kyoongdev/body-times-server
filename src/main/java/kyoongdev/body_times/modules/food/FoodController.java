package kyoongdev.body_times.modules.food;


import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.UUID;
import kyoongdev.body_times.modules.food.dto.FoodDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "음식")
@RequestMapping("/foods")
@RestController
@RequiredArgsConstructor
public class FoodController {

  private final FoodService foodService;

  @GetMapping("{foodId}/detail")
  public FoodDTO findFoods(@PathVariable(value = "foodId") String foodId) {
    return foodService.findFoodById(UUID.fromString(foodId));
  }


}
