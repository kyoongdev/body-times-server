package kyoongdev.body_times.modules.meal;

import kyoongdev.body_times.modules.meal.dto.query.FindMealQuery;
import kyoongdev.body_times.modules.meal.entities.Meal;
import org.springframework.data.domain.Page;

public interface MealCustomRepository {

  Page<Meal> findMealsByUserId(String userId, FindMealQuery query);

}
