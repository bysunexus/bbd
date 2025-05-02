package in.fireye.bbd.server.ai.mcp;

import in.fireye.bbd.commons.DateUtils;
import in.fireye.bbd.server.dto.BbdLedgerDto;
import in.fireye.bbd.server.service.IFeeTypeService;
import in.fireye.bbd.server.service.ILedgerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 这里有个坑，因为是ai调用函数如果抛错本地并不会记录（也许是spring ai还有些不完善，后续应该会做处理，异常应该在本地打印）
 * 这里的处理做法是将每个方法均做try catch，如果抛错，则记录日志，并返回错误信息
 * 请求参数尽量定义为直接参数，如果定义对象接受 有可能会导致ai调用函数失败
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class BbdService {

  private final IFeeTypeService feeTypeService;
  private final ILedgerService ledgerService;

  @Tool(description = "获取所有的一级支出分类")
  public List<FeeTypeDto> getFirstLevelFeeType() {
    log.info("获取所有一级支出分类");
    try {
      return feeTypeService.getFeeTypeByParentId(0).stream().map(FeeTypeDto::new).toList();
    } catch (Exception e) {
      log.error("获取一级支出分类失败", e);
      throw new RuntimeException("获取一级支出分类失败", e);
    }
  }


  @Tool(description = "根据一级分类id获取其下的所有二级支出分类")
  public List<FeeTypeDto> getSecondLevelFeeType(@ToolParam(description = "一级支出分类id") Integer feeTypeId) {
    log.info("获取二级支出分类，feeTypeId={}", feeTypeId);
    try {
      return feeTypeService.getFeeTypeByParentId(feeTypeId).stream().map(FeeTypeDto::new).toList();
    } catch (Exception e) {
      log.error("获取二级支出分类失败", e);
      throw new RuntimeException("获取二级支出分类失败", e);
    }
  }

  @Tool(description = "记录一笔费用支出，其中feeTypeId支出分类id为二级支出分类的id，需要先判断属于哪个一级支出分类，再判断二级支出分类")
  public LedgerResultDto recordLedger(
    @ToolParam(description = "二级支出类型id")
    Integer feeTypeId,
    @ToolParam(description = "支出金额")
    BigDecimal amount,
    @ToolParam(description = "支出发生的日期，格式为：YYYYMMDD，默认为今天", required = false)
    String feeDate,
    @ToolParam(description = "支出的详细描述")
    String desc
  ) {
    log.info("记录一笔费用支出，feeTypeId={},amount={},feeDate={},desc={}", feeTypeId, amount, feeDate, desc);
    try {
      BbdLedgerDto bbdLedgerDto = new BbdLedgerDto();
      bbdLedgerDto.setFeeTypeId(feeTypeId);
      bbdLedgerDto.setAmount(amount);
      LocalDateTime dateTime;
      if (StringUtils.isNotBlank(feeDate)) {
        dateTime = DateUtils.parse(feeDate, DateUtils.YYYYMMDD);
      } else {
        dateTime = DateUtils.getToday();
      }

      bbdLedgerDto.setFeeDate(dateTime);
      bbdLedgerDto.setDesc(desc);

      BbdLedgerDto result = ledgerService.createOutlayLedger(bbdLedgerDto);

      LedgerResultDto ledgerResultDto = new LedgerResultDto();
      ledgerResultDto.setLedgerId(result.getLedgerId());
      ledgerResultDto.setFeeTypeId(result.getFeeTypeId());
      ledgerResultDto.setAmount(result.getAmount());
      ledgerResultDto.setFeeDate(DateUtils.format(result.getFeeDate(), DateUtils.YYYYMMDD));
      ledgerResultDto.setDesc(result.getDesc());
      log.info("记录一笔费用支出成功，result={}", ledgerResultDto);
      return ledgerResultDto;
    } catch (Exception e) {
      log.error("记录一笔费用支出失败", e);
      throw new RuntimeException("记录一笔费用支出失败", e);
    }
  }

  @Tool(description = "获取今天，格式为：YYYYMMDD")
  public String getToday() {
    log.info("获取今天");
    try {
      return DateUtils.getTodayString(DateUtils.YYYYMMDD);
    } catch (Exception e) {
      log.error("获取今天失败", e);
      throw new RuntimeException("获取今天失败", e);
    }
  }

  @Tool(description = "获取昨天，格式为：YYYYMMDD")
  public String getYesterday() {
    log.info("获取昨天");
    try {
      return DateUtils.format(DateUtils.minusDays(DateUtils.getToday(), 1), DateUtils.YYYYMMDD);
    } catch (Exception e) {
      log.error("获取昨天失败", e);
      throw new RuntimeException("获取昨天失败", e);
    }
  }

  @Tool(description = "获取前天，格式为：YYYYMMDD")
  public String getTheDayBeforeYesterday() {
    log.info("获取前天");
    try {
      return DateUtils.format(DateUtils.minusDays(DateUtils.getToday(), 2), DateUtils.YYYYMMDD);
    } catch (Exception e) {
      log.error("获取前天失败", e);
      throw new RuntimeException("获取前天失败", e);
    }
  }

  @Tool(description = "以今天为基础计算日期，格式为：YYYYMMDD")
  public String calculateDate(@ToolParam(description = "日期偏移量，例如计算明天则为1，计算昨天则为-1") int offset) {
    log.info("以今天为基础计算日期，offset={}", offset);
    try {
      return DateUtils.format(DateUtils.dayPlus(DateUtils.getToday(), offset), DateUtils.YYYYMMDD);
    } catch (Exception e) {
      log.error("计算日期失败", e);
      throw new RuntimeException("计算日期失败", e);
    }
  }


}
