package kyoongdev.body_times.common.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


@AllArgsConstructor
@Getter
public class PagingDTO {

  private int page;
  private int limit;

  public Pageable toPageable() {
    return PageRequest.of(page, limit);
  }

}
