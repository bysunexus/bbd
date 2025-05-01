package in.fireye.bbd.server.ai.mcp;

import in.fireye.bbd.server.dto.BbdLedgerDto;
import in.fireye.bbd.server.service.IFeeTypeService;
import in.fireye.bbd.server.service.ILedgerService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BbdService {

  private final IFeeTypeService feeTypeService;
  private final ILedgerService ledgerService;
  private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");

  @Tool(description = "获取所有的一级支出分类")
  public List<FeeTypeDto> getFirstLevelFeeType() {
    return feeTypeService.getFeeTypeByParentId(0).stream().map(FeeTypeDto::new).toList();
  }


  @Tool(description = "根据一级分类id获取其下的所有二级支出分类")
  public List<FeeTypeDto> getSecondLevelFeeType(@ToolParam(description = "一级支出分类id") Integer feeTypeId) {
    return feeTypeService.getFeeTypeByParentId(feeTypeId).stream().map(FeeTypeDto::new).toList();
  }

  @Tool(description = "记录一笔费用支出，其中feeTypeId支出分类id为二级支出分类的id，需要先判断属于哪个一级支出分类，再判断二级支出分类")
  public LedgerResultDto recordLedger(LedgerDto ledgerDto) {
    BbdLedgerDto bbdLedgerDto = new BbdLedgerDto();
    bbdLedgerDto.setFeeTypeId(ledgerDto.getFeeTypeId());
    bbdLedgerDto.setAmount(ledgerDto.getAmount());
    LocalDateTime dateTime = LocalDateTime.parse(ledgerDto.getFeeDate(), FORMATTER);
    bbdLedgerDto.setFeeDate(dateTime);
    bbdLedgerDto.setDesc(ledgerDto.getDesc());

    BbdLedgerDto result = ledgerService.createOutlayLedger(bbdLedgerDto);

    LedgerResultDto ledgerResultDto = new LedgerResultDto();
    ledgerResultDto.setLedgerId(result.getLedgerId());
    ledgerResultDto.setFeeTypeId(result.getFeeTypeId());
    ledgerResultDto.setAmount(result.getAmount());
    ledgerResultDto.setFeeDate(result.getFeeDate().format(FORMATTER));
    ledgerResultDto.setDesc(result.getDesc());

    return ledgerResultDto;
  }

}
