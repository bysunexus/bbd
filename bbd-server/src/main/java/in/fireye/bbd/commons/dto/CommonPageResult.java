package in.fireye.bbd.commons.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
public class CommonPageResult<T> implements Serializable {
  @Serial
  private static final long serialVersionUID = -4042872179598135700L;

  /**
   * 当前页码
   */
  private int pageNumber;
  /**
   * 每页数量
   */
  private int pageSize;
  /**
   * 总页数
   */
  private long total;
  /**
   * 总记录数
   */
  private int totalPages;

  /**
   * 数据列表
   */
  private List<T> content;
}
