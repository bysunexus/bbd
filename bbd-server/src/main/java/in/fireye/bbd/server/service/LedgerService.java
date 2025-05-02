package in.fireye.bbd.server.service;


import in.fireye.bbd.commons.dto.Deleted;
import in.fireye.bbd.commons.dto.InOutSign;
import in.fireye.bbd.commons.dto.ResponseCode;
import in.fireye.bbd.commons.exception.BbdBusinessException;
import in.fireye.bbd.server.dto.BbdLedgerDto;
import in.fireye.bbd.server.entity.BbdLedger;
import in.fireye.bbd.server.repository.BbdLedgerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class LedgerService implements ILedgerService {
  private final BbdLedgerRepository ledgerRepository;

  private BbdLedgerDto createLedger(BbdLedgerDto ledgerDto, InOutSign inOutSign) {
    log.info("记录一笔费用，ledgerDto={}", ledgerDto);
    BbdLedger ledger = new BbdLedger();
    ledger.setInOutSign(inOutSign.getCode());
    ledger.setFeeTypeId(ledgerDto.getFeeTypeId());
    ledger.setAmount(ledgerDto.getAmount());
    ledger.setFeeDate(ledgerDto.getFeeDate());
    // todo:sby 待登录完成后此处修改为获取当前登录用户id
    ledger.setCreateUser(0);
    ledger.setModifyUser(0);
    ledger.setCreateTime(LocalDateTime.now());
    ledger.setModifyTime(LocalDateTime.now());
    ledger.setDeleted(Deleted.NO.getCode());
    ledger.setDesc(ledgerDto.getDesc());
    ledgerRepository.saveAndFlush(ledger);
    ledgerDto.setLedgerId(ledger.getLedgerId());
    return ledgerDto;
  }

  @Override
  public BbdLedgerDto createOutlayLedger(BbdLedgerDto ledgerDto) {
    log.info("记录一笔费用支出，ledgerDto={}", ledgerDto);
    return createLedger(ledgerDto, InOutSign.OUT);
  }

  @Override
  public BbdLedgerDto createRevenueLedger(BbdLedgerDto ledgerDto) {
    log.info("记录一笔费用收入，ledgerDto={}", ledgerDto);
    return createLedger(ledgerDto, InOutSign.IN);
  }

  @Override
  public void updateLedger(BbdLedgerDto ledgerDto) {
    Optional<BbdLedger> ledgerOptional = ledgerRepository.findByIdAndDeleted(ledgerDto.getLedgerId(), Deleted.NO.getCode());
    BbdLedger ledger = ledgerOptional.orElseThrow(() -> new BbdBusinessException(ResponseCode.PARAM_ERROR));

    ledger.setFeeTypeId(ledgerDto.getFeeTypeId());
    ledger.setAmount(ledgerDto.getAmount());
    ledger.setFeeDate(ledgerDto.getFeeDate());
    ledger.setDesc(ledgerDto.getDesc());
    // todo:sby 待登录完成后此处修改为获取当前登录用户id
    ledger.setModifyUser(0);
    ledger.setModifyTime(LocalDateTime.now());
    ledgerRepository.save(ledger);
  }

  @Override
  public void deleteLedger(Integer ledgerId) {

    Optional<BbdLedger> ledgerOptional = ledgerRepository.findByIdAndDeleted(ledgerId, Deleted.NO.getCode());
    BbdLedger ledger = ledgerOptional.orElseThrow(() -> new BbdBusinessException(ResponseCode.PARAM_ERROR));
    ledger.setDeleted(Deleted.YES.getCode());
    // todo:sby 待登录完成后此处修改为获取当前登录用户id
    ledger.setModifyUser(0);
    ledger.setModifyTime(LocalDateTime.now());
    ledgerRepository.save(ledger);
  }
}
