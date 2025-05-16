package in.fireye.bbd.server.repository;

import in.fireye.bbd.server.entity.BbdLedger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BbdLedgerRepository extends JpaRepository<BbdLedger, Integer>, JpaSpecificationExecutor<BbdLedger> {
  @Query("select l from BbdLedger l where l.ledgerId = :id and l.deleted = :code")
  Optional<BbdLedger> findByIdAndDeleted(Integer id, String code);
}
