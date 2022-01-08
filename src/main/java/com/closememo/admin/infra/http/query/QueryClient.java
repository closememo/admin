package com.closememo.admin.infra.http.query;

import com.closememo.admin.infra.http.query.response.QueryNoticePageResponse;
import com.closememo.admin.infra.http.query.response.QueryNoticeResponse;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class QueryClient {

  private static final String X_SYSTEM_KEY_HEADER = "X-SYSTEM-KEY";

  private final RestTemplate restTemplate;

  public QueryClient(RestTemplateBuilder restTemplateBuilder,
      QueryProperties properties) {

    this.restTemplate = restTemplateBuilder
        .rootUri(properties.getRootUri())
        .defaultHeader(X_SYSTEM_KEY_HEADER, properties.getToken())
        .build();
  }

  public QueryNoticePageResponse getNotices(int page, int limit) {
    String uriString = UriComponentsBuilder.fromPath("/query/system/notices")
        .queryParam("page", page)
        .queryParam("limit", limit)
        .toUriString();

    RequestEntity<Void> requestEntity = RequestEntity
        .get(uriString)
        .build();

    ResponseEntity<QueryNoticePageResponse> response =
        restTemplate.exchange(requestEntity, QueryNoticePageResponse.class);

    validateResponse(response, "[QUERY] getNotices failed.");

    return response.getBody();
  }

  public QueryNoticeResponse getNotice(String noticeId) {
    RequestEntity<Void> requestEntity = RequestEntity
        .get("/query/system/notices/" + noticeId)
        .build();

    ResponseEntity<QueryNoticeResponse> response =
        restTemplate.exchange(requestEntity, QueryNoticeResponse.class);

    validateResponse(response, "[QUERY] getNotice failed.");

    return response.getBody();
  }

  private void validateResponse(@NonNull ResponseEntity<?> response, String errorMessage) {
    if (response.getStatusCode().is5xxServerError()) {
      throw new QueryInternalServerException(errorMessage);
    }

    if (response.getStatusCode().isError()) {
      throw new QueryClientException(errorMessage);
    }
  }
}
