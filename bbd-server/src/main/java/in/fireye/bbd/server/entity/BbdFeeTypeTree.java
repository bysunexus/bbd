package in.fireye.bbd.server.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bbd_fee_type_tree")
@IdClass(BbdFeeTypeTreeId.class)
public class BbdFeeTypeTree {
  @Id
  private Integer ancestorId;
  @Id
  private Integer descendantId;

  @Column(name = "distance", nullable = false)
  private Integer distance;
}