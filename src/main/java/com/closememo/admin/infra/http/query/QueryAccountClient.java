package com.closememo.admin.infra.http.query;

import com.closememo.admin.infra.http.query.response.QueryAccountResponse;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class QueryAccountClient extends QueryClient {

  public QueryAccountClient(QueryRestTemplateProxy restTemplate) {
    super(restTemplate);
  }

  public QueryAccountResponse getAccountByEmail(String email) {
    String uriString = UriComponentsBuilder.fromPath("/query/system/account-by-email")
        .queryParam("email", email)
        .toUriString();

    RequestEntity<Void> requestEntity = RequestEntity
        .get(uriString)
        .build();

    ResponseEntity<QueryAccountResponse> response =
        restTemplate.exchange(requestEntity, QueryAccountResponse.class);

    validateResponse(response, "[QUERY] getAccountByEmail failed.");

    return response.getBody();
  }
}
