package kyoongdev.body_times.modules.meal;


import kyoongdev.body_times.common.paging.PaginationDTO;
import kyoongdev.body_times.modules.meal.dto.MealDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindMealsResponseDTO extends PaginationDTO<MealDTO> {

}
