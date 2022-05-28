package com.closememo.admin.infra.http.query;

import com.closememo.admin.infra.http.query.response.QueryNotificationResponse;
import com.closememo.admin.infra.http.query.response.QueryPageResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class QueryNotificationClient extends QueryClient {

  public QueryNotificationClient(QueryRestTemplateProxy restTemplate) {
    super(restTemplate);
  }

  public QueryPageResponse<QueryNotificationResponse> getNotifications(int page, int limit) {
    String uriString = UriComponentsBuilder.fromPath("/query/system/notifications")
        .queryParam("page", page)
        .queryParam("limit", limit)
        .toUriString();

    RequestEntity<Void> requestEntity = RequestEntity
        .get(uriString)
        .build();

    ParameterizedTypeReference<QueryPageResponse<QueryNotificationResponse>> type =
        new ParameterizedTypeReference<>() {
        };

    ResponseEntity<QueryPageResponse<QueryNotificationResponse>> response =
        restTemplate.exchange(requestEntity, type);

    validateResponse(response, "[QUERY] getNotifications failed.");

    return response.getBody();
  }

  public QueryNotificationResponse getNotification(String notificationId) {
    RequestEntity<Void> requestEntity = RequestEntity
        .get("/query/system/notifications/" + notificationId)
        .build();

    ResponseEntity<QueryNotificationResponse> response =
        restTemplate.exchange(requestEntity, QueryNotificationResponse.class);

    validateResponse(response, "[QUERY] getNotification failed.");

    return response.getBody();
  }

  public QueryNotificationResponse getCurrentNotification() {
    RequestEntity<Void> requestEntity = RequestEntity
        .get("/query/system/current-notification")
        .build();

    ResponseEntity<QueryNotificationResponse> response =
        restTemplate.exchange(requestEntity, QueryNotificationResponse.class);

    validateResponse(response, "[QUERY] getCurrentNotification failed.");

    return response.getBody();
  }
}
