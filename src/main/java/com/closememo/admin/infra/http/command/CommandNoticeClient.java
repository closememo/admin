package com.closememo.admin.infra.http.command;

import com.closememo.admin.infra.http.command.request.CommandCreateNoticeRequest;
import com.closememo.admin.infra.http.command.request.CommandDeleteNoticeRequest;
import com.closememo.admin.infra.http.command.request.CommandUpdateNoticeRequest;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CommandNoticeClient extends CommandClient {

  public CommandNoticeClient(CommandRestTemplateProxy restTemplate) {
    super(restTemplate);
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
}
