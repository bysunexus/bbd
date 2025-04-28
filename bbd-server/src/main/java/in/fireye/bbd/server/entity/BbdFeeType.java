package in.fireye.bbd.server.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "bbd_fee_type")
public class BbdFeeType {
  @Id
  @Column(name = "fee_type_id", nullable = false)
  private Integer id;

  @Column(name = "name_l1", nullable = false, length = 100)
  private String nameL1;

  @Column(name = "name_l2", nullable = false, length = 100)
  private String nameL2;

  @Column(name = "name_l3", nullable = false, length = 100)
  private String nameL3;

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