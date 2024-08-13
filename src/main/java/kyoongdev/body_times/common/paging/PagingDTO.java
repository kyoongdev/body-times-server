package kyoongdev.body_times.common.paging;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


@Getter
@Setter
@ParameterObject
@Schema(description = "PagingDTO")
public class PagingDTO {

  @Schema(description = "페이지", nullable = false)
  private Integer page;

  @Schema(description = "개수", nullable = false)
  private Integer limit;

  public Pageable toPageable() {
    return PageRequest.of(page - 1, limit);
  }

}
