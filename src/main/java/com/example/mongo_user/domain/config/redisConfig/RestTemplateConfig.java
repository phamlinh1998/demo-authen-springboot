package com.example.mongo_user.domain.config.redisConfig;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@Slf4j
public class RestTemplateConfig {
  @Bean
  public RestTemplate rest(RestTemplateBuilder builder) {
    return builder.build();
  }
}
