package in.fireye.bbd.server.ai.mcp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DirectResultDto<T> {

  private T data;
  private DirectResultDataType dataType;

}
