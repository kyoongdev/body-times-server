package kyoongdev.body_times.modules.food;


import java.util.Optional;
import java.util.UUID;
import kyoongdev.body_times.common.dto.ResponseWithIdDTO;
import kyoongdev.body_times.common.exception.CustomException;
import kyoongdev.body_times.common.paging.PaginationDTO;
import kyoongdev.body_times.common.paging.PagingDTO;
import kyoongdev.body_times.modules.food.dto.CreateFoodDTO;
import kyoongdev.body_times.modules.food.dto.FoodDTO;
import kyoongdev.body_times.modules.food.dto.UpdateFoodDTO;
import kyoongdev.body_times.modules.food.dto.query.FindFoodsQuery;
import kyoongdev.body_times.modules.food.entities.Food;
import kyoongdev.body_times.modules.food.exception.FoodErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class FoodService {

  private final FoodRepository foodRepository;


  FoodDTO findFoodById(UUID id) {

    Optional<Food> food = foodRepository.findById(id);

    if (food.isEmpty()) {
      throw new CustomException(FoodErrorCode.NOT_FOUND);
    }

    return FoodDTO.fromEntity(food.get());
  }


  PaginationDTO<FoodDTO> findFoods(PagingDTO paging, FindFoodsQuery query) {
    log.info("이름 : " + query.getName());
    Page<Food> foods = foodRepository.findAllByName(query.getName(), paging.toPageable());

    return PaginationDTO.of(foods.map(FoodDTO::fromEntity), paging, foods.getTotalElements());
  }

  ResponseWithIdDTO createFoods(CreateFoodDTO data) {
    Food food = foodRepository.save(data.toEntity());

    return new ResponseWithIdDTO(food.getId().toString());
  }

  void updateFood(UUID id, UpdateFoodDTO data) {
    findFoodById(id);

  }

  void deleteFood(UUID id) {
    findFoodById(id);

    foodRepository.deleteById(id);
  }


}
