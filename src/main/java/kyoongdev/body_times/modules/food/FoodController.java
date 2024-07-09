package kyoongdev.body_times.modules.food;


import io.swagger.v3.oas.annotations.tags.Tag;
import kyoongdev.body_times.common.annotations.V1RestController;
import kyoongdev.body_times.modules.food.dto.CreateFoodDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@Tag(name = "음식")
@V1RestController(value = "/foods")
public class FoodController {

  @PostMapping("")
  public void createFood(@RequestBody() CreateFoodDTO body) {
  }

}
