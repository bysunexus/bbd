package in.fireye.bbd.config;

import in.fireye.bbd.tools.DateTimeTools;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 在同工程内没必要以mcp server 和 mcp client 的方式进行集成
 * 可以采用这里提供 ToolCallbackProvider 的方式进行集成
 *
 * @see ChatConfig
 */

@Configuration
public class McpServerConfig {
  @Bean
  public ToolCallbackProvider bbdToolCallbacks(DateTimeTools dateTimeTools) {
    return MethodToolCallbackProvider.builder()
      .toolObjects(dateTimeTools)
      .build();
  }

}
