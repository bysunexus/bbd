package in.fireye.bbd.server.ai.mcp;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class LedgerResultDto {
  private Integer ledgerId;
  private Integer feeTypeId;
  private BigDecimal amount;
  private String feeDate;
  private String desc;
}
