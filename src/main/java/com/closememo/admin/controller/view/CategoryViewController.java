package com.closememo.admin.controller.view;

import org.springframework.web.bind.annotation.GetMapping;

@ViewController
public class CategoryViewController {

  @GetMapping("/category")
  public String category() {
    return "category/main";
  }
}
