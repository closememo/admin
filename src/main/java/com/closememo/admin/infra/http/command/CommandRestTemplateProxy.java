package com.closememo.admin.infra.http.command;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CommandRestTemplateProxy {

  private static final String X_SYSTEM_KEY_HEADER = "X-SYSTEM-KEY";

  private final RestTemplate restTemplate;

  public CommandRestTemplateProxy(RestTemplateBuilder restTemplateBuilder,
      CommandProperties properties) {

    this.restTemplate = restTemplateBuilder
        .rootUri(properties.getRootUri())
        .defaultHeader(X_SYSTEM_KEY_HEADER, properties.getToken())
        .build();
  }

  public <T> ResponseEntity<T> exchange(RequestEntity<?> entity, Class<T> responseType) {
    return restTemplate.exchange(entity, responseType);
  }
}
