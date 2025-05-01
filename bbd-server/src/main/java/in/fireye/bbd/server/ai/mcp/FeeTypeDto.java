package in.fireye.bbd.server.ai.mcp;

import in.fireye.bbd.server.dto.BbdFeeTypeDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FeeTypeDto {
  private Integer feeTypeId;
  private String typeName;
  private Integer parentId;

  public FeeTypeDto(BbdFeeTypeDto feeType) {
    this.feeTypeId = feeType.getFeeTypeId();
    this.typeName = feeType.getTypeName();
    this.parentId = feeType.getParentId();
  }
}
