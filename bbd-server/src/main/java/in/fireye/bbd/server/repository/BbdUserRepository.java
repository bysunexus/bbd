package in.fireye.bbd.server.repository;

import in.fireye.bbd.server.entity.BbdUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BbdUserRepository extends JpaRepository<BbdUser, Integer> {
}