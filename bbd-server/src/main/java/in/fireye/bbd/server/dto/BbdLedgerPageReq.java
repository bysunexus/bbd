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

  private String inOutSign;
  private String startDate;
  private String endDate;
  private Integer feeTypeId;
}
