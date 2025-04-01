package in.fireye.bbd.tools;

import org.joda.time.DateTime;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

@Component
public class DateTimeTools {
  @Tool(description = "获取今天当前具体的日期和时间")
  String getCurrentDateTime() {
    return DateTime.now().toString("yyyy-MM-dd HH:mm:ss EEE");
  }

}
