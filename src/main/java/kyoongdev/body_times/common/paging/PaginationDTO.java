package kyoongdev.body_times.common.paging;


import java.util.List;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Builder
@Getter
public class PaginationDTO<T> {

  private List<T> data;

  private PageMetaDTO paging;


  public static <T> PaginationDTO<T> of(Page<T> data, PagingDTO paging, int count) {
    return PaginationDTO.<T>builder()
        .data(data.getContent())
        .paging(PageMetaDTO.fromPagingDTO(paging, count))
        .build();
  }
}
