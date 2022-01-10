package com.closememo.admin.infra.http.command;

import com.closememo.admin.infra.http.command.request.CommandChangeSuggestionStatusRequest;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CommandSuggestionClient extends CommandClient {

  public CommandSuggestionClient(CommandRestTemplateProxy restTemplate) {
    super(restTemplate);
  }

  public void changeSuggestionStatus(CommandChangeSuggestionStatusRequest request) {
    RequestEntity<CommandChangeSuggestionStatusRequest> requestEntity = RequestEntity
        .post("/command/system/change-suggestion-status")
        .accept(MediaType.APPLICATION_JSON)
        .body(request);

    ResponseEntity<Void> response = restTemplate.exchange(requestEntity, Void.class);

    validateResponse(response, "[COMMAND] changeSuggestionStatus failed.");
  }
}
