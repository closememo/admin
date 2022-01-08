package com.closememo.admin.controller.view;

import org.springframework.web.bind.annotation.GetMapping;

@ViewController
public class AccountViewController {

  @GetMapping("")
  public String index() {
    return "main";
  }

  @GetMapping("/login")
  public String login() {
    return "login";
  }
}
