package com.closememo.admin.controller.api.request;

import lombok.Getter;

@Getter
public class ChangeSuggestionStatusRequest {

  private String suggestionId;
  private String status;
}
