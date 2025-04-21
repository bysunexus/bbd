package in.fireye.demo.chat.config;

import in.fireye.demo.chat.tools.DateTimeTools;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatConfig {

  /**
   * 在同工程内没必要以mcp server 和 mcp client 的方式进行集成
   * 可以采用这里提供 ToolCallbackProvider 的方式进行集成
   */
  @Bean
  public ToolCallbackProvider bbdToolCallbacks(DateTimeTools dateTimeTools) {
    return MethodToolCallbackProvider.builder()
      .toolObjects(dateTimeTools)
      .build();
  }

  /**
   * 创建一个ChatClient对象，并设置默认的系统提示语和工具。
   * 修改未统一构建的chatClient，避免每次创建chatClient时都需要设置系统提示语和工具。
   *
   * @param builder          chatClient的构造器
   * @param bbdToolCallbacks 内部的工具回调提供者
   * @param mcpToolCallbacks mcp工具回调提供者
   * @return chatClient对象
   */
  @Bean
  public ChatClient chatClient(ChatClient.Builder builder, ToolCallbackProvider bbdToolCallbacks, ToolCallbackProvider mcpToolCallbacks) {
    return builder
      .defaultSystem("你叫550W，你是一个生活助手，可以帮助用户查询生活相关信息。" +
        "你可以查询时间日期，计算时间日期，查询天气等。" +
        "回复时，请使用简洁友好的语言。")
      .defaultTools(bbdToolCallbacks, mcpToolCallbacks)
      .build();
  }
}
