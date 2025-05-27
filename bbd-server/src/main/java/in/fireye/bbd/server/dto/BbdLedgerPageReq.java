package in.fireye.bbd.server.dto;

import in.fireye.bbd.commons.dto.CommonPageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

@EqualsAndHashCode(callSuper = true)
@Data
public class BbdLedgerPageReq extends CommonPageRequest {
  @Serial
  private static final long serialVersionUID = -2880941823250550198L;

  /**
   * 收支标志 I-收入 O-支出
   */
  private String inOutSign;
  /**
   * 开始时间 格式:yyyyMMdd
   */
  private String startDate;
  /**
   * 结束时间 默认为今天 格式:yyyyMMdd
   */
  private String endDate;
  /**
   * 费用类型ID
   */
  private Integer feeTypeId;
}
