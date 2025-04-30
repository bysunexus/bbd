package in.fireye.bbd.server.dto;

import in.fireye.bbd.server.entity.BbdLedger;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO for {@link in.fireye.bbd.server.entity.BbdLedger}
 */
@NoArgsConstructor
@Data
public class BbdLedgerDto implements Serializable {
  @Serial
  private static final long serialVersionUID = 850731854733029928L;
  private Integer id;
  private String inOutSign;
  private Integer feeTypeId;
  private BigDecimal amount;
  private LocalDateTime feeDate;
  private String desc;

  public BbdLedgerDto(BbdLedger ledger) {
    this.id = ledger.getId();
    this.inOutSign = ledger.getInOutSign();
    this.feeTypeId = ledger.getFeeTypeId();
    this.amount = ledger.getAmount();
    this.feeDate = ledger.getFeeDate();
    this.desc = ledger.getDesc();
  }
}