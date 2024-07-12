package kyoongdev.body_times.common.dto;


import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PageMetaDTO {

  private int total;


  private int number;


  private int limit;

  private boolean hasPrev;


  private boolean hasNext;


  static PageMetaDTO fromPagingDTO(PagingDTO paging, int count) {
    int total = count;
    int page = paging.getPage();
    int limit = paging.getLimit();
    boolean hasPrev = page > 1;
    boolean hasNext = page * limit < count;

    return new PageMetaDTO(total, page, limit, hasPrev, hasNext);

  }


}
