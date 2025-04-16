package in.fireye.bbd.tools;

import org.joda.time.DateTime;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

@Component
public class DateTimeTools {
  @Tool(description = "获取当前具体的日期和时间")
  public String getCurrentDateTime() {
    return DateTime.now().toString("yyyy-MM-dd HH:mm:ss EEE");
  }

  @Tool(description = "基于当前时间根据输入偏移量参数计算日期")
  public String getDate(@ToolParam(description = "偏移量", required = true) int count) {
    return DateTime.now().plusDays(count).toString("yyyy-MM-dd");
  }
}
