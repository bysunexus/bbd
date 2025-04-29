package in.fireye.bbd.server.entity;

import jakarta.persistence.Column;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class BbdFeeTypeTreeId implements Serializable {

  @Serial
  private static final long serialVersionUID = 5021409453688467580L;
  @Column(name = "ancestor_id", nullable = false)
  private Integer ancestorId;
  @Column(name = "descendant_id", nullable = false)
  private Integer descendantId;
}
