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

  @DeleteMapping(value = "/{ledgerId}")
  public CommonResponse<Void> deleteLedger(@PathVariable("ledgerId") Integer ledgerId) {
    ledgerService.deleteLedger(ledgerId);
    return CommonResponse.ok();
  }

  @PostMapping(value = "/")
  public CommonResponse<BbdLedgerDto> createLedger(@RequestBody BbdLedgerDto ledgerDto) {
    BbdLedgerDto ledger = ledgerService.createOutlayLedger(ledgerDto);
    return CommonResponse.ok(ledger);
  }

  @PutMapping(value = "/")
  public CommonResponse<Void> updateLedger(@RequestBody BbdLedgerDto ledgerDto) {
    ledgerService.updateLedger(ledgerDto);
    return CommonResponse.ok();
  }

  @GetMapping(value = "/{ledgerId}")
  public CommonResponse<BbdLedgerDto> getLedger(@PathVariable("ledgerId") Integer ledgerId) {
    BbdLedgerDto ledger = ledgerService.getLedgerById(ledgerId);
    return CommonResponse.ok(ledger);
  }


  @GetMapping(value = "/")
  public CommonResponse<CommonPageResult<BbdLedgerDto>> getLedgers(BbdLedgerPageReq req) {
    CommonPageResult<BbdLedgerDto> ledgers = ledgerService.findLedgers(req);
    return CommonResponse.ok(ledgers);
  }


}
