package in.fireye.bbd.server.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "bbd_fee_type")
public class BbdFeeType {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "fee_type_id", nullable = false)
  private Integer feeTypeId;

  @Column(name = "type_name", nullable = false, length = 100)
  private String typeName;

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

  @Column(name = "parent_id", nullable = false)
  private Integer parentId;

}
