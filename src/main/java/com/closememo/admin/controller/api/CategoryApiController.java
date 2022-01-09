package com.closememo.admin.controller.api;

import com.closememo.admin.controller.api.request.RefreshCategoryRequest;
import com.closememo.admin.service.CategoryService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@AdminApiController
public class CategoryApiController {

  private final CategoryService categoryService;

  public CategoryApiController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @PostMapping("/refresh-category")
  public void refreshCategory(@RequestBody RefreshCategoryRequest request) {
    categoryService.refreshCategoryByEmail(request.getEmail());
  }
}
