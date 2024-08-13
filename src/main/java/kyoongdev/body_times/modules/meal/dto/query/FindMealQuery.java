package kyoongdev.body_times.modules.meal.dto.query;

import com.querydsl.core.types.dsl.BooleanExpression;
import io.swagger.v3.oas.annotations.media.Schema;
import kyoongdev.body_times.common.paging.PagingDTO;
import kyoongdev.body_times.modules.meal.entities.QMeal;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Schema(name = "FindMealQuery")
public class FindMealQuery extends PagingDTO {

  @Schema(type = "number", nullable = true)
  Integer year;

  @Schema(type = "number", nullable = true)
  Integer month;

  @Schema(type = "number", nullable = true)
  Integer day;


  public BooleanExpression eqYear(QMeal meal) {
    if (year == null || year.describeConstable().isEmpty()) {
      return null;
    }

    return meal.year.eq(year);
  }

  public BooleanExpression eqMonth(QMeal meal) {
    if (month == null || month.describeConstable().isEmpty()) {
      return null;
    }

    return meal.month.eq(month);
  }

  public BooleanExpression eqDay(QMeal meal) {
    if (day == null || day.describeConstable().isEmpty()) {
      return null;
    }

    return meal.day.eq(day);
  }
}
