package in.fireye.bbd.server.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "bbd_ledger")
public class BbdLedger {
  @Id
  @Column(name = "ledger_id", nullable = false)
  private Integer id;

  @Column(name = "in_out_sign", nullable = false, length = 1)
  private String inOutSign;

  @Column(name = "fee_type_id", nullable = false)
  private Integer feeTypeId;

  @Column(name = "account_id")
  private Integer accountId;

  @Column(name = "amount", nullable = false)
  private BigDecimal amount;

  @Column(name = "fee_date", nullable = false)
  private Instant feeDate;

  @Column(name = "create_user", nullable = false)
  private Integer createUser;

  @Column(name = "modify_user", nullable = false)
  private Integer modifyUser;

  @Column(name = "create_time", nullable = false)
  private Instant createTime;

  @Column(name = "modify_time", nullable = false)
  private Instant modifyTime;

  @ColumnDefault("'0'")
  @Column(name = "deleted", nullable = false, length = 1)
  private String deleted;

}