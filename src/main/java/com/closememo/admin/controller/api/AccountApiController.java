package com.closememo.admin.controller.api;

import com.closememo.admin.controller.api.request.CreateAccountRequest;
import com.closememo.admin.dto.AccountDTO;
import com.closememo.admin.service.AccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@AdminApiController
public class AccountApiController {

  private final AccountService accountService;

  public AccountApiController(AccountService accountService) {
    this.accountService = accountService;
  }

  @PostMapping("/create-account")
  public String createAccount(@RequestBody CreateAccountRequest request) {
    return accountService.createAccount(request.getUsername(), request.getPassword());
  }

  @GetMapping("/account-by-username")
  public AccountDTO getAccountByUsername(@RequestParam String username) {
    return accountService.getAccountByUsername(username);
  }
}
