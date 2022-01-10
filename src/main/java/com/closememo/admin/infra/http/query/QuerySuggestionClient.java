package com.closememo.admin.infra.http.query;

import com.closememo.admin.infra.http.query.response.QueryPageResponse;
import com.closememo.admin.infra.http.query.response.QuerySuggestionResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class QuerySuggestionClient extends QueryClient {

  public QuerySuggestionClient(QueryRestTemplateProxy restTemplate) {
    super(restTemplate);
  }

  public QueryPageResponse<QuerySuggestionResponse> getSuggestions(int page, int limit, String status) {
    UriComponentsBuilder uriStringBuilder = UriComponentsBuilder.fromPath("/query/system/suggestions")
        .queryParam("page", page)
        .queryParam("limit", limit);

    if (StringUtils.isNotBlank(status)) {
      uriStringBuilder.queryParam("status", status);
    }

    RequestEntity<Void> requestEntity = RequestEntity
        .get(uriStringBuilder.toUriString())
        .build();

    ParameterizedTypeReference<QueryPageResponse<QuerySuggestionResponse>> type =
        new ParameterizedTypeReference<>() {
        };

    ResponseEntity<QueryPageResponse<QuerySuggestionResponse>> response =
        restTemplate.exchange(requestEntity, type);

    validateResponse(response, "[QUERY] getSuggestions failed.");

    return response.getBody();
  }
}
