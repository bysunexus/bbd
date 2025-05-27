package in.fireye.bbd.server.controller;


import in.fireye.bbd.server.vo.MessageVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/bbd-server/ai")
@RequiredArgsConstructor
@Slf4j
public class AiController {


  private final ChatClient chatClient;

  // 如使用Server-Sent Events 需要将 produces = MediaType.TEXT_EVENT_STREAM_VALUE，此处仅以流方式返回

  /**
   * ai助手对话接口
   *
   * @param message 用户对话消息
   * @return ai响应消息
   */
  @PostMapping(value = "/chat")
  public Flux<String> time(@RequestBody MessageVO message) {

    return chatClient.prompt(message.getMessage())
      .stream()
      .chatResponse()
      .map(r -> {
        if (r.getResult() == null || r.getResult().getOutput() == null
          || r.getResult().getOutput().getText() == null) {
          return "";
        }
        return StringUtils.trimToEmpty(r.getResult().getOutput().getText());
      }).filter(StringUtils::isNotBlank);
  }

}
