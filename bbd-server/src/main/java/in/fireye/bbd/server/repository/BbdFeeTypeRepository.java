package in.fireye.bbd.server.repository;

import in.fireye.bbd.server.entity.BbdFeeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface BbdFeeTypeRepository extends JpaRepository<BbdFeeType, Integer> {

  @Modifying
  @Query("""
      update BbdFeeType t set t.deleted = '1' ,t.modifyTime = :modifyTime, t.modifyUser = :modifyUser
      where t.id in (select tt.descendantId from BbdFeeTypeTree tt where tt.ancestorId = :feeTypeId)
    """)
  void deleteFeeType(Integer feeTypeId, LocalDateTime modifyTime, Integer modifyUser);

  @Query("select t from BbdFeeType t where t.parentId = :parentId and t.deleted = '0'")
  List<BbdFeeType> getFeeTypeByParentId(Integer parentId);

  @Query("select t from BbdFeeType t where t.deleted = :code")
  List<BbdFeeType> findAllByDeleted(String code);

  @Query("""
      select t from BbdFeeType t where t.deleted = '0' and exists (
        select 1 from BbdFeeTypeTree tt where t.id = tt.descendantId and tt.ancestorId = :parentId
      )
    """)
  List<BbdFeeType> getFeeTypeTreeByParentId(Integer parentId);

  @Query("""
      select t from BbdFeeType t where t.deleted = '0' and exists (
        select 1 from BbdFeeTypeTree tt where t.id = tt.ancestorId and tt.descendantId = :feeTypeId
      )
    """)
  List<BbdFeeType> getFeeTypeTreeById(Integer feeTypeId);

}