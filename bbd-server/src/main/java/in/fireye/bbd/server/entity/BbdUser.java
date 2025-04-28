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
@Table(name = "bbd_users")
public class BbdUser {
  @Id
  @Column(name = "user_id", nullable = false)
  private Integer id;

  @Column(name = "user_name", nullable = false, length = 50)
  private String userName;

  @Column(name = "nickname", nullable = false, length = 50)
  private String nickname;

  @Column(name = "email", nullable = false, length = 500)
  private String email;

  @Column(name = "password", nullable = false, length = 200)
  private String password;

  @Column(name = "create_user", nullable = false)
  private Integer createUser;

  @Column(name = "modify_user", nullable = false)
  private Integer modifyUser;

  @Column(name = "create_time", nullable = false)
  private Instant createTime;

  @Column(name = "modify_time", nullable = false)
  private Instant modifyTime;

  @ColumnDefault("'1'")
  @Column(name = "enabled", nullable = false, length = 1)
  private String enabled;

  @ColumnDefault("'0'")
  @Column(name = "deleted", nullable = false, length = 1)
  private String deleted;

}