package in.fireye.bbd.server.controller;

import in.fireye.bbd.commons.dto.CommonPageResult;
import in.fireye.bbd.commons.dto.CommonResponse;
import in.fireye.bbd.server.dto.BbdLedgerDto;
import in.fireye.bbd.server.dto.BbdLedgerPageReq;
import in.fireye.bbd.server.service.ILedgerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bbd-server/ledgers")
@RequiredArgsConstructor
@Slf4j
public class LedgerController {

  private final ILedgerService ledgerService;

  /**
   * 删除记账
   *
   * @param ledgerId 记账id
   * @return 操作结果
   */
  @DeleteMapping(value = "/{ledgerId}")
  public CommonResponse<Void> deleteLedger(@PathVariable("ledgerId") Integer ledgerId) {
    ledgerService.deleteLedger(ledgerId);
    return CommonResponse.ok();
  }

  /**
   * 创建记账
   * @param ledgerDto 记账信息
   * @return 创建成功的记账信息
   */
  @PostMapping(value = "/")
  public CommonResponse<BbdLedgerDto> createLedger(@RequestBody BbdLedgerDto ledgerDto) {
    BbdLedgerDto ledger = ledgerService.createOutlayLedger(ledgerDto);
    return CommonResponse.ok(ledger);
  }

  /**
   * 更新记账
   * @param ledgerDto 记账信息
   * @return 操作结果
   */
  @PutMapping(value = "/")
  public CommonResponse<Void> updateLedger(@RequestBody BbdLedgerDto ledgerDto) {
    ledgerService.updateLedger(ledgerDto);
    return CommonResponse.ok();
  }

  /**
   * 查询记账
   * @param ledgerId 记账id
   * @return 记账信息
   */
  @GetMapping(value = "/{ledgerId}")
  public CommonResponse<BbdLedgerDto> getLedger(@PathVariable("ledgerId") Integer ledgerId) {
    BbdLedgerDto ledger = ledgerService.getLedgerById(ledgerId);
    return CommonResponse.ok(ledger);
  }

  /**
   * 查询记账分页列表
   * @param req 查询条件
   * @return 记账列表
   */
  @GetMapping(value = "/")
  public CommonResponse<CommonPageResult<BbdLedgerDto>> getLedgers(BbdLedgerPageReq req) {
    CommonPageResult<BbdLedgerDto> ledgers = ledgerService.findLedgers(req);
    return CommonResponse.ok(ledgers);
  }


}
