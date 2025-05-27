package in.fireye.bbd.server.dto;

import in.fireye.bbd.server.entity.BbdLedger;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO for {@link in.fireye.bbd.server.entity.BbdLedger}
 */
@NoArgsConstructor
@Data
public class BbdLedgerDto implements Serializable {
  @Serial
  private static final long serialVersionUID = 850731854733029928L;
  /**
   * 记账id
   */
  private Integer ledgerId;
  /**
   * 收支标志 I-收入 O-支出
   */
  private String inOutSign;
  /**
   * 收支类别id
   */
  private Integer feeTypeId;
  /**
   * 收支类别名称
   */
  private String feeTypeName;
  /**
   * 金额
   */
  private BigDecimal amount;
  /**
   * 账单日期 格式:yyyyMMdd
   */
  private LocalDate feeDate;
  /**
   * 描述
   */
  private String desc;

  public BbdLedgerDto(BbdLedger ledger) {
    this.ledgerId = ledger.getLedgerId();
    this.inOutSign = ledger.getInOutSign();
    this.feeTypeId = ledger.getFeeTypeId();
    this.amount = ledger.getAmount();
    this.feeDate = ledger.getFeeDate();
    this.desc = ledger.getDesc();
  }
}
