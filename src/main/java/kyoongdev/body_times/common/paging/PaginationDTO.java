package kyoongdev.body_times.common.paging;


import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;


@Getter
@Setter
@Schema(description = "페이지네이션 DTO")
public class PaginationDTO<T> {


  @Schema(description = "데이터")
  private List<T> data;


  @Schema(description = "페이지 정보")
  private PageMetaDTO paging;


  public static <T> PaginationDTO<T> of(Page<T> data, PagingDTO paging, Long count) {
    PaginationDTO<T> pagination = new PaginationDTO<>();

    pagination.setData(data.getContent());
    pagination.setPaging(PageMetaDTO.fromPagingDTO(paging, count.intValue()));

    return pagination;
  }
}
