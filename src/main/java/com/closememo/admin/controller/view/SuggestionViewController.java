package com.closememo.admin.controller.view;

import com.closememo.admin.dto.SuggestionDTO;
import com.closememo.admin.dto.shared.OffsetPage;
import com.closememo.admin.service.SuggestionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@ViewController
public class SuggestionViewController {

  private final SuggestionService suggestionService;

  public SuggestionViewController(SuggestionService suggestionService) {
    this.suggestionService = suggestionService;
  }

  @GetMapping("/suggestion")
  public ModelAndView suggestionList(
      @RequestParam(defaultValue = "1") Integer page,
      @RequestParam(defaultValue = "10") Integer limit,
      @RequestParam(required = false) String status) {

    OffsetPage<SuggestionDTO> suggestionPage =
        suggestionService.getSuggestions(page, limit, status);

    ModelAndView modelAndView = new ModelAndView("suggestion/main");
    modelAndView.addObject("page", suggestionPage);
    return modelAndView;
  }
}
