package com.closememo.admin.infra.http.query;

import com.closememo.admin.infra.http.query.request.QueryRefreshCategoryRequest;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class QueryCategoryClient extends QueryClient{

  public QueryCategoryClient(QueryRestTemplateProxy restTemplate) {
    super(restTemplate);
  }

  public void refreshCategory(QueryRefreshCategoryRequest request) {
    RequestEntity<QueryRefreshCategoryRequest> requestEntity = RequestEntity
        .post("/query/system/refresh-category")
        .accept(MediaType.APPLICATION_JSON)
        .body(request);

    ResponseEntity<Void> response = restTemplate.exchange(requestEntity, Void.class);

    validateResponse(response, "[QUERY] refreshCategory failed.");
  }
}
