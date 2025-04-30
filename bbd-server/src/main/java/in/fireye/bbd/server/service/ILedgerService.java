package in.fireye.bbd.server.service;

import in.fireye.bbd.server.dto.BbdLedgerDto;

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
   * @return 账单DTO
   */
  void updateLedger(BbdLedgerDto ledgerDto);

  /**
   * 更新账单
   *
   * @param ledgerId 账单id
   */
  void deleteLedger(Integer ledgerId);
}
