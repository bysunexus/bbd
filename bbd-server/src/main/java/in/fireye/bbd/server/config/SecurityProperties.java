package in.fireye.bbd.server.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(
  prefix = "bbd.security"
)
@Data
public class SecurityProperties {
  private String[] anonymousPaths;
}
