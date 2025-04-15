package in.fireye.bbd.service;

import in.fireye.bbd.tools.DateTimeTools;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.model.tool.ToolCallingChatOptions;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class DemoService {
  private final OllamaChatModel ollamaChatModel;
  private final DateTimeTools dateTimeTools;

  public Flux<String> chat(String msg) {
    // ollama 在使用tool calling的时候不支持stream
    ChatOptions chatOptions = ToolCallingChatOptions.builder()
//      .toolCallbacks(ToolCallbacks.from(dateTimeTools))
      .build();
    return ollamaChatModel
      .stream(new Prompt(msg, chatOptions))
      .map(response ->
        (response.getResult() == null || response.getResult().getOutput() == null || response.getResult().getOutput().getText() == null) ?
          "" : response.getResult().getOutput().getText()
      );
  }
}
