package in.fireye.bbd.commons.dto;

import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.io.Serial;
import java.io.Serializable;

@Data
public class CommonPageRequest implements Serializable {
  @Serial
  private static final long serialVersionUID = 8772952530230005684L;

  /**
   * 页码，从0开始
   */
  private int pageNumber;
  /**
   * 每页数量
   */
  private int pageSize;


  public Pageable toPageable() {
    return PageRequest.of(pageNumber, pageSize);
  }
}
