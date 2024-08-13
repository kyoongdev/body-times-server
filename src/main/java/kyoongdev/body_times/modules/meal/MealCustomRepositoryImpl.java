package kyoongdev.body_times.modules.meal;


import static kyoongdev.body_times.modules.meal.entities.QMeal.meal;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import kyoongdev.body_times.modules.food.entities.QFood;
import kyoongdev.body_times.modules.meal.dto.query.FindMealQuery;
import kyoongdev.body_times.modules.meal.entities.Meal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class MealCustomRepositoryImpl extends QuerydslRepositorySupport implements
    MealCustomRepository {

  @Autowired
  private JPAQueryFactory queryFactory;


  public MealCustomRepositoryImpl() {
    super(Meal.class);
  }


  @Override
  public Page<Meal> findMealsByUserId(String userId, FindMealQuery query) {

    QFood food = QFood.food;

    List<Meal> meals = queryFactory.selectDistinct(meal).from(meal).leftJoin(food)
        .on(food.id.eq(meal.food.id))
        .where(query.eqYear(meal), query.eqMonth(meal), query.eqDay(meal))
        .offset(query.getPage() - 1)
        .limit(query.getLimit())
        .fetch();

    Long count = queryFactory.select(meal.count()).from(meal)
        .where(query.eqYear(meal), query.eqMonth(meal), query.eqDay(meal)).fetchOne();

    assert count != null;

    return new PageImpl<>(meals, query.toPageable(), count.intValue());
  }


}
