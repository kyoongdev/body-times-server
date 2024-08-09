package kyoongdev.body_times.modules.meal;

import kyoongdev.body_times.modules.meal.dto.MealDTO;
import kyoongdev.body_times.modules.meal.dto.query.FindMealQuery;
import org.springframework.data.domain.Page;

public interface MealCustomRepository {

  Page<MealDTO> findManyMeals(String userId, FindMealQuery query);

}
