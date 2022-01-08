package com.closememo.admin.infra.http.query;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@ConfigurationProperties("http.query")
@Configuration
public class QueryProperties {

  private String rootUri;
  private String token;
}
