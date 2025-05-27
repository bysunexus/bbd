package in.fireye.bbd.server.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "bbd_ledger")
public class BbdLedger {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ledger_id", nullable = false)
  private Integer ledgerId;

  @Column(name = "in_out_sign", nullable = false, length = 1)
  private String inOutSign;

  @Column(name = "fee_type_id", nullable = false)
  private Integer feeTypeId;

  @Column(name = "amount", nullable = false)
  private BigDecimal amount;

  @Column(name = "fee_date", columnDefinition = "TIMESTAMP", nullable = false)
  private LocalDate feeDate;

  @Column(name = "create_user", nullable = false)
  private Integer createUser;

  @Column(name = "modify_user", nullable = false)
  private Integer modifyUser;

  @CreationTimestamp
  @Column(name = "create_time", columnDefinition = "TIMESTAMP", nullable = false)
  private Instant createTime;

  @UpdateTimestamp
  @Column(name = "modify_time", columnDefinition = "TIMESTAMP", nullable = false)
  private Instant modifyTime;

  @ColumnDefault("'0'")
  @Column(name = "deleted", nullable = false, length = 1)
  private String deleted;

  @Column(name = "desc", nullable = false, length = 500)
  private String desc;

}
