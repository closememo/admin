package com.closememo.admin.infra.http.command;

import com.closememo.admin.infra.http.command.request.CommandCreateNoticeRequest;
import com.closememo.admin.infra.http.command.request.CommandDeleteNoticeRequest;
import com.closememo.admin.infra.http.command.request.CommandUpdateNoticeRequest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CommandClient {

  private static final String X_SYSTEM_KEY_HEADER = "X-SYSTEM-KEY";

  private final RestTemplate restTemplate;

  public CommandClient(RestTemplateBuilder restTemplateBuilder,
      CommandProperties properties) {

    this.restTemplate = restTemplateBuilder
        .rootUri(properties.getRootUri())
        .defaultHeader(X_SYSTEM_KEY_HEADER, properties.getToken())
        .build();
  }

  public void createNotice(CommandCreateNoticeRequest request) {
    RequestEntity<CommandCreateNoticeRequest> requestEntity = RequestEntity
        .post("/command/system/create-notice")
        .accept(MediaType.APPLICATION_JSON)
        .body(request);

    ResponseEntity<Void> response = restTemplate.exchange(requestEntity, Void.class);

    validateResponse(response, "[COMMAND] createNotice failed.");
  }

  public void updateNotice(CommandUpdateNoticeRequest request) {
    RequestEntity<CommandUpdateNoticeRequest> requestEntity = RequestEntity
        .post("/command/system/update-notice")
        .accept(MediaType.APPLICATION_JSON)
        .body(request);

    ResponseEntity<Void> response = restTemplate.exchange(requestEntity, Void.class);

    validateResponse(response, "[COMMAND] updateNotice failed.");
  }

  public void deleteNotice(CommandDeleteNoticeRequest request) {
    RequestEntity<CommandDeleteNoticeRequest> requestEntity = RequestEntity
        .post("/command/system/delete-notice")
        .accept(MediaType.APPLICATION_JSON)
        .body(request);

    ResponseEntity<Void> response = restTemplate.exchange(requestEntity, Void.class);

    validateResponse(response, "[COMMAND] deleteNotice failed.");
  }

  private void validateResponse(@NonNull ResponseEntity<?> response, String errorMessage) {
    if (response.getStatusCode().is5xxServerError()) {
      throw new CommandInternalServerException(errorMessage);
    }

    if (response.getStatusCode().isError()) {
      throw new CommandClientException(errorMessage);
    }
  }
}
