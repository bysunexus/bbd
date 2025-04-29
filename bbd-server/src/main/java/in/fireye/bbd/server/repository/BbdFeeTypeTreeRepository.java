package in.fireye.bbd.server.repository;

import in.fireye.bbd.server.entity.BbdFeeTypeTree;
import in.fireye.bbd.server.entity.BbdFeeTypeTreeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BbdFeeTypeTreeRepository extends JpaRepository<BbdFeeTypeTree, BbdFeeTypeTreeId> {
  @Query("select t from BbdFeeTypeTree t where t.descendantId = :descendantId")
  List<BbdFeeTypeTree> findByDescendantId(Integer descendantId);

  @Modifying
  @Query("""
      delete from BbdFeeTypeTree t where t.descendantId in (
        select tt.descendantId from BbdFeeTypeTree tt where tt.ancestorId = :feeTypeId
      )
    """)
  void deleteByDescendantId(Integer feeTypeId);
}