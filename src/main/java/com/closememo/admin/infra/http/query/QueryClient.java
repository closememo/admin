package com.closememo.admin.infra.http.query;

import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;

public abstract class QueryClient {

  protected QueryRestTemplateProxy restTemplate;

  public QueryClient(QueryRestTemplateProxy restTemplate) {
    this.restTemplate = restTemplate;
  }

  protected void validateResponse(@NonNull ResponseEntity<?> response, String errorMessage) {
    if (response.getStatusCode().is5xxServerError()) {
      throw new QueryInternalServerException(errorMessage);
    }

    if (response.getStatusCode().isError()) {
      throw new QueryClientException(errorMessage);
    }
  }
}
