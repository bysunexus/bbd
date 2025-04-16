package in.fireye.bbd.service;

import in.fireye.bbd.tools.DateTimeTools;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.model.tool.ToolCallingChatOptions;
import org.springframework.ai.tool.ToolCallbacks;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class DemoService {
  private final ChatModel chatModel;
  private final DateTimeTools dateTimeTools;

  public Flux<String> chat(String msg) {
    // ollama 在使用tool calling的时候不支持stream
    ChatOptions chatOptions = ToolCallingChatOptions.builder()
      // 添加的 tool calling 可以 询问 “今天几号” 来进行测试
      .toolCallbacks(ToolCallbacks.from(dateTimeTools))
      .build();
    return chatModel
      .stream(new Prompt(msg, chatOptions))
      .map(response ->
        (response.getResult() == null || response.getResult().getOutput() == null || response.getResult().getOutput().getText() == null) ?
          "" : response.getResult().getOutput().getText()
      );
  }
}
