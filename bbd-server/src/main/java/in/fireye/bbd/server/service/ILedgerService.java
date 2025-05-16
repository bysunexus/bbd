package in.fireye.bbd.server.service;

import in.fireye.bbd.commons.dto.CommonPageResult;
import in.fireye.bbd.server.dto.BbdLedgerDto;
import in.fireye.bbd.server.dto.BbdLedgerPageReq;

public interface ILedgerService {

  /**
   * 创建支出账单
   *
   * @param ledgerDto 账单DTO
   * @return 账单DTO
   */
  BbdLedgerDto createOutlayLedger(BbdLedgerDto ledgerDto);

  /**
   * 创建收入账单
   *
   * @param ledgerDto 账单DTO
   * @return 账单DTO
   */
  BbdLedgerDto createRevenueLedger(BbdLedgerDto ledgerDto);

  /**
   * 更新账单
   *
   * @param ledgerDto 账单DTO
   */
  void updateLedger(BbdLedgerDto ledgerDto);

  /**
   * 更新账单
   *
   * @param ledgerId 账单id
   */
  void deleteLedger(Integer ledgerId);

  /**
   * 根据账单id获取账单
   *
   * @param ledgerId 账单id
   * @return 账单
   */
  BbdLedgerDto getLedgerById(Integer ledgerId);

  /**
   * 分页查询账单
   *
   * @param req 查询条件
   * @return 分页列表
   */
  CommonPageResult<BbdLedgerDto> findLedgers(BbdLedgerPageReq req);
}
