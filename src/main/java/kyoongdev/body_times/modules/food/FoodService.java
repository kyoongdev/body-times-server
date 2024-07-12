package kyoongdev.body_times.modules.food;


import java.util.Optional;
import java.util.UUID;
import kyoongdev.body_times.modules.food.dto.FoodDTO;
import kyoongdev.body_times.modules.food.entities.Food;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FoodService {

  private final FoodRepository foodRepository;


  FoodDTO findFoodById(UUID id){

    Optional<Food> food = foodRepository.findById(id);

    if(food.isEmpty()){
//      throw new
    }

    return FoodDTO.fromEntity(food.get());
  }



}
