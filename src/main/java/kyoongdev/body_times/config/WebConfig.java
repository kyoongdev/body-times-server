package kyoongdev.body_times.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class WebConfig {

  @Bean
  public CustomRequestMappingHandlerMapping customRequestMappingHandlerMapping() {
    return new CustomRequestMappingHandlerMapping();
  }

}
