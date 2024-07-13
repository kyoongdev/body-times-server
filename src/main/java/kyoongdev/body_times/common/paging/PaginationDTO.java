package kyoongdev.body_times.common.paging;


import lombok.Builder;

@Builder
public class PaginationDTO<T> {

  private T[] data;

  private PageMetaDTO paging;


  static <T> PaginationDTO of(T[] data, PagingDTO paging, int count) {
    return PaginationDTO.builder()
        .data(data)
        .paging(PageMetaDTO.fromPagingDTO(paging, count))
        .build();
  }


}
