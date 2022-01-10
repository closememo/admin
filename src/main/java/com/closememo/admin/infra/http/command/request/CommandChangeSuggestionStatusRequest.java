package com.closememo.admin.infra.http.command.request;

import lombok.Getter;

@Getter
public class CommandChangeSuggestionStatusRequest {

  private final String suggestionId;
  private final String status;

  public CommandChangeSuggestionStatusRequest(String suggestionId, String status) {
    this.suggestionId = suggestionId;
    this.status = status;
  }
}
