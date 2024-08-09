package kyoongdev.body_times.modules.meal;


import java.util.Optional;
import java.util.UUID;
import kyoongdev.body_times.common.dto.ResponseWithIdDTO;
import kyoongdev.body_times.common.exception.CustomException;
import kyoongdev.body_times.modules.food.FoodRepository;
import kyoongdev.body_times.modules.food.entities.Food;
import kyoongdev.body_times.modules.food.exception.FoodErrorCode;
import kyoongdev.body_times.modules.meal.dto.CreateMealDTO;
import kyoongdev.body_times.modules.meal.dto.MealDTO;
import kyoongdev.body_times.modules.meal.dto.UpdateMealDTO;
import kyoongdev.body_times.modules.meal.entities.Meal;
import kyoongdev.body_times.modules.meal.exception.MealErrorCode;
import kyoongdev.body_times.modules.user.UserRepository;
import kyoongdev.body_times.modules.user.entities.User;
import kyoongdev.body_times.modules.user.exception.UserErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MealService {

  private final MealRepository mealRepository;
  private final UserRepository userRepository;
  private final FoodRepository foodRepository;


  MealDTO findMyMealById(String id, String userId) {

    Meal meal = checkMealAndUser(id, userId);

    return MealDTO.fromEntity(meal);
  }


  ResponseWithIdDTO createMeal(String userId, CreateMealDTO data) {
    Optional<User> user = userRepository.findById(UUID.fromString(userId));

    if (user.isEmpty()) {
      throw new CustomException(UserErrorCode.NOT_FOUND);
    }

    Optional<Food> food = foodRepository.findById(UUID.fromString(data.getFoodId()));

    if (food.isEmpty()) {
      throw new CustomException(FoodErrorCode.NOT_FOUND);
    }

    Meal meal = mealRepository.save(data.toEntity(food.get(), user.get()));

    return new ResponseWithIdDTO(meal.getId().toString());
  }

  void updateMeal(String mealId, String userId, UpdateMealDTO data) {

    Meal meal = checkMealAndUser(mealId, userId);

    mealRepository.save(data.toEntity(meal));
  }

  void deleteMeal(String mealId, String userId) {

    Meal meal = checkMealAndUser(mealId, userId);

    mealRepository.deleteById(meal.getId());
  }

  Meal checkMealAndUser(String mealId, String userId) {
    Optional<Meal> meal = mealRepository.findById(UUID.fromString((mealId)));

    if (meal.isEmpty()) {
      throw new CustomException(MealErrorCode.NOT_FOUND);
    }

    Optional<User> user = userRepository.findById(UUID.fromString(userId));

    if (user.isEmpty()) {
      throw new CustomException(UserErrorCode.NOT_FOUND);
    }

    if (meal.get().getUser().getId() != user.get().getId()) {
      throw new CustomException(MealErrorCode.NOT_MY_MEAL);
    }

    return meal.get();
  }


}
