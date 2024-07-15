package kyoongdev.body_times.modules.food.dto.query;

import io.swagger.v3.oas.annotations.media.Schema;
import kyoongdev.body_times.common.paging.PagingDTO;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Schema(name = "FindFoodsQuery")
public class FindFoodsQuery extends PagingDTO {

  @Schema(description = "이름", nullable = true, type = "string")
  private String name;


}
