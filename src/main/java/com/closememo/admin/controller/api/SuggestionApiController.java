package com.closememo.admin.controller.api;

import com.closememo.admin.controller.api.request.ChangeSuggestionStatusRequest;
import com.closememo.admin.service.SuggestionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@AdminApiController
public class SuggestionApiController {

  private final SuggestionService suggestionService;

  public SuggestionApiController(SuggestionService suggestionService) {
    this.suggestionService = suggestionService;
  }

  @PostMapping("/change-suggestion-status")
  public void changeSuggestionStatus(@RequestBody ChangeSuggestionStatusRequest request) {
    suggestionService.changeSuggestionStatus(request.getSuggestionId(), request.getStatus());
  }
}
