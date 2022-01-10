package com.closememo.admin.infra.http.query;

import com.closememo.admin.infra.http.query.response.QueryNoticeResponse;
import com.closememo.admin.infra.http.query.response.QueryPageResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class QueryNoticeClient extends QueryClient {

  public QueryNoticeClient(QueryRestTemplateProxy restTemplate) {
    super(restTemplate);
  }

  public QueryPageResponse<QueryNoticeResponse> getNotices(int page, int limit) {
    String uriString = UriComponentsBuilder.fromPath("/query/system/notices")
        .queryParam("page", page)
        .queryParam("limit", limit)
        .toUriString();

    RequestEntity<Void> requestEntity = RequestEntity
        .get(uriString)
        .build();

    ParameterizedTypeReference<QueryPageResponse<QueryNoticeResponse>> type =
        new ParameterizedTypeReference<>() {
        };

    ResponseEntity<QueryPageResponse<QueryNoticeResponse>> response =
        restTemplate.exchange(requestEntity, type);

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
}
