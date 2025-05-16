package in.fireye.bbd.commons.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
public class CommonPageResult<T> implements Serializable {
  @Serial
  private static final long serialVersionUID = -4042872179598135700L;

  private int pageNumber;
  private int pageSize;
  private long total;
  private int totalPages;

  private List<T> content;
}
