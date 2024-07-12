package kyoongdev.body_times.modules.food;


import java.util.UUID;
import kyoongdev.body_times.modules.food.entities.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends JpaRepository<Food, UUID> {

}
