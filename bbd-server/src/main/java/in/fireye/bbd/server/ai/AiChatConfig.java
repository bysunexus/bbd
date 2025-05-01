package in.fireye.bbd.server.ai;

import in.fireye.bbd.server.ai.mcp.BbdService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiChatConfig {
  /**
   * 在同工程内没必要以mcp server 和 mcp client 的方式进行集成
   * 可以采用这里提供 ToolCallbackProvider 的方式进行集成
   */
  @Bean
  public ToolCallbackProvider bbdToolCallbacks(BbdService bbdService) {
    return MethodToolCallbackProvider.builder()
      .toolObjects(bbdService)
      .build();
  }

  /**
   * 创建一个ChatClient对象，并设置默认的系统提示语和工具。
   * 修改未统一构建的chatClient，避免每次创建chatClient时都需要设置系统提示语和工具。
   *
   * @param builder          chatClient的构造器
   * @param bbdToolCallbacks 内部的工具回调提供者
   * @return chatClient对象
   */
  @Bean
  public ChatClient chatClient(ChatClient.Builder builder, ToolCallbackProvider bbdToolCallbacks) {
    return builder
      .defaultSystem("""
          你叫逼逼叨，你是一个记账小工具，你可以使用系统提供的工具来完成一级分类查询、二级分类查询、记账和账本统计等功能。
          回复时，请使用简洁友好的语言，对于非记账相关的提问你可以拒绝回答。
        """)
      .defaultTools(bbdToolCallbacks)
      .build();
  }
}
