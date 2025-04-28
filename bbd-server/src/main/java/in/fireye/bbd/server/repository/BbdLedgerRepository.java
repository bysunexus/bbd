package in.fireye.bbd.server.repository;

import in.fireye.bbd.server.entity.BbdLedger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BbdLedgerRepository extends JpaRepository<BbdLedger, Integer> {
}