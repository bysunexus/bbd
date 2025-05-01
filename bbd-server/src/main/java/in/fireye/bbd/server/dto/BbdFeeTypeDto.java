package in.fireye.bbd.server.dto;

import in.fireye.bbd.server.entity.BbdFeeType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * DTO for {@link BbdFeeType}
 */
@NoArgsConstructor
@Data
public class BbdFeeTypeDto implements Serializable {
  @Serial
  private static final long serialVersionUID = 982568530253999217L;
  private Integer feeTypeId;
  private String typeName;
  private Integer parentId;

  public BbdFeeTypeDto(BbdFeeType feeType) {
    this.feeTypeId = feeType.getFeeTypeId();
    this.typeName = feeType.getTypeName();
    this.parentId = feeType.getParentId();
  }

}