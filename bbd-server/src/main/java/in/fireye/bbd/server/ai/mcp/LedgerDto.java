package in.fireye.bbd.server.ai.mcp;

import lombok.Data;
import org.springframework.ai.tool.annotation.ToolParam;

import java.math.BigDecimal;

@Data
public class LedgerDto {
  @ToolParam(description = "二级支出类型id")
  private Integer feeTypeId;
  @ToolParam(description = "支出金额")
  private BigDecimal amount;
  @ToolParam(description = "支出发生的日期，格式为：YYYYMMDD")
  private String feeDate;
  @ToolParam(description = "支出的详细描述")
  private String desc;
}
