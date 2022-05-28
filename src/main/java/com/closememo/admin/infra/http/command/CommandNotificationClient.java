package com.closememo.admin.infra.http.command;

import com.closememo.admin.infra.http.command.request.CommandActivateNotificationRequest;
import com.closememo.admin.infra.http.command.request.CommandCreateNotificationRequest;
import com.closememo.admin.infra.http.command.request.CommandDeleteNotificationRequest;
import com.closememo.admin.infra.http.command.request.CommandInactivateNotificationRequest;
import com.closememo.admin.infra.http.command.request.CommandUpdateNotificationRequest;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CommandNotificationClient extends CommandClient {

  public CommandNotificationClient(CommandRestTemplateProxy restTemplate) {
    super(restTemplate);
  }

  public void createNotification(CommandCreateNotificationRequest request) {
    RequestEntity<CommandCreateNotificationRequest> requestEntity = RequestEntity
        .post("/command/system/create-notification")
        .accept(MediaType.APPLICATION_JSON)
        .body(request);

    ResponseEntity<Void> response = restTemplate.exchange(requestEntity, Void.class);

    validateResponse(response, "[COMMAND] createNotification failed.");
  }

  public void updateNotification(CommandUpdateNotificationRequest request) {
    RequestEntity<CommandUpdateNotificationRequest> requestEntity = RequestEntity
        .post("/command/system/update-notification")
        .accept(MediaType.APPLICATION_JSON)
        .body(request);

    ResponseEntity<Void> response = restTemplate.exchange(requestEntity, Void.class);

    validateResponse(response, "[COMMAND] updateNotification failed");
  }

  public void activateNotification(CommandActivateNotificationRequest request) {
    RequestEntity<CommandActivateNotificationRequest> requestEntity = RequestEntity
        .post("/command/system/activate-notification")
        .accept(MediaType.APPLICATION_JSON)
        .body(request);

    ResponseEntity<Void> response = restTemplate.exchange(requestEntity, Void.class);

    validateResponse(response, "[COMMAND] activateNotification failed.");
  }

  public void inactivateNotification(CommandInactivateNotificationRequest request) {
    RequestEntity<CommandInactivateNotificationRequest> requestEntity = RequestEntity
        .post("/command/system/inactivate-notification")
        .accept(MediaType.APPLICATION_JSON)
        .body(request);

    ResponseEntity<Void> response = restTemplate.exchange(requestEntity, Void.class);

    validateResponse(response, "[COMMAND] inactivateNotification failed.");
  }

  public void deleteNotification(CommandDeleteNotificationRequest request) {
    RequestEntity<CommandDeleteNotificationRequest> requestEntity = RequestEntity
        .post("/command/system/delete-notification")
        .accept(MediaType.APPLICATION_JSON)
        .body(request);

    ResponseEntity<Void> response = restTemplate.exchange(requestEntity, Void.class);

    validateResponse(response, "[COMMAND] deleteNotification failed.");
  }
}
