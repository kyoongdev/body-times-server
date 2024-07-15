package kyoongdev.body_times.modules.food;


import java.util.UUID;
import kyoongdev.body_times.modules.food.entities.Food;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends JpaRepository<Food, UUID> {


  @Query("SELECT e FROM food e WHERE :name IS NULL OR :name = '' OR e.name LIKE %:name%")
  Page<Food> findAllByName(String name, Pageable page);
}
