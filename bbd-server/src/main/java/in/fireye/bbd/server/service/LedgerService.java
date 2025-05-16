package in.fireye.bbd.server.service;


import in.fireye.bbd.commons.DateUtils;
import in.fireye.bbd.commons.dto.CommonPageResult;
import in.fireye.bbd.commons.dto.Deleted;
import in.fireye.bbd.commons.dto.InOutSign;
import in.fireye.bbd.commons.dto.ResponseCode;
import in.fireye.bbd.commons.exception.BbdBusinessException;
import in.fireye.bbd.server.dto.BbdLedgerDto;
import in.fireye.bbd.server.dto.BbdLedgerPageReq;
import in.fireye.bbd.server.entity.BbdFeeType;
import in.fireye.bbd.server.entity.BbdLedger;
import in.fireye.bbd.server.repository.BbdFeeTypeRepository;
import in.fireye.bbd.server.repository.BbdLedgerRepository;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class LedgerService implements ILedgerService {
  private final BbdLedgerRepository ledgerRepository;
  private final BbdFeeTypeRepository feeTypeRepository;

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
    String feeTypeName = getFeeTypeName(ledger.getFeeTypeId());
    ledgerDto.setFeeTypeName(feeTypeName);

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

  @Override
  public BbdLedgerDto getLedgerById(Integer ledgerId) {
    BbdLedger ledger = ledgerRepository.getReferenceById(ledgerId);
    BbdLedgerDto ledgerDto = new BbdLedgerDto(ledger);
    String feeTypeName = getFeeTypeName(ledger.getFeeTypeId());
    ledgerDto.setFeeTypeName(feeTypeName);
    return ledgerDto;
  }

  private String getFeeTypeName(Integer feeTypeId) {
    return feeTypeRepository.getFeeTypeTreeById(feeTypeId)
      .stream()
      .map(BbdFeeType::getTypeName)
      .collect(Collectors.joining(" - "));
  }

  @Override
  public CommonPageResult<BbdLedgerDto> findLedgers(BbdLedgerPageReq req) {

    Specification<BbdLedger> specification = (root, query, cb) -> {
      List<Predicate> predicateList = new ArrayList<>();

      if (StringUtils.isNotBlank(req.getStartDate())) {
        LocalDateTime date = DateUtils.parseAtStartOfDay(req.getStartDate(), DateUtils.YYYYMMDD);
        predicateList.add(cb.greaterThanOrEqualTo(root.get("feeDate"), date));
      }
      if (StringUtils.isNotBlank(req.getEndDate())) {
        LocalDateTime date = DateUtils.parseAtEndOfDay(req.getEndDate(), DateUtils.YYYYMMDD);
        predicateList.add(cb.lessThan(root.get("feeDate"), date));
      }
      if (StringUtils.isNotBlank(req.getInOutSign())) {
        predicateList.add(cb.equal(root.get("inOutSign"), InOutSign.IN.getCode().equals(req.getInOutSign()) ? InOutSign.IN.getCode() : InOutSign.OUT.getCode()));
      }
      if (null != req.getFeeTypeId()) {
        predicateList.add(cb.equal(root.get("feeTypeId"), req.getFeeTypeId()));
      }
      predicateList.add(cb.equal(root.get("deleted"), Deleted.NO.getCode()));
      Predicate[] p = new Predicate[predicateList.size()];
      query.where(predicateList.toArray(p));
      return query.getRestriction();
    };
    Page<BbdLedger> datas = ledgerRepository.findAll(specification, req.toPageable());
    CommonPageResult<BbdLedgerDto> result = new CommonPageResult<>();
    result.setPageNumber(req.getPageNumber());
    result.setPageSize(req.getPageSize());
    result.setTotal(datas.getTotalElements());
    result.setTotalPages(datas.getTotalPages());
    result.setContent(datas.stream().map(t -> {
      BbdLedgerDto dto = new BbdLedgerDto(t);
      dto.setFeeTypeName(getFeeTypeName(t.getFeeTypeId()));
      return dto;
    }).collect(Collectors.toList()));


    return null;
  }
}
