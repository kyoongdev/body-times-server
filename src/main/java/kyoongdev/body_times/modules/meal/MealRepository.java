package kyoongdev.body_times.modules.meal;


import java.util.List;
import java.util.Optional;
import java.util.UUID;
import kyoongdev.body_times.modules.meal.entities.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealRepository extends JpaRepository<Meal, UUID> {

  Optional<Meal> findMealById(UUID id);

  List<Meal> findMealsByYearAndMonthAndDay(int year, int month, int day);


}
