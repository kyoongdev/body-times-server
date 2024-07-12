package kyoongdev.body_times.modules.food;


import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import kyoongdev.body_times.modules.food.dto.CreateFoodDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "음식")
@RequestMapping("/foods")
@RestController
public class FoodController {

  @GetMapping("")
  public List<?> findFoods(){
    return List.of();
  }

  @PostMapping("")
  public void createFood(@RequestBody() CreateFoodDTO body) {

  }

}
