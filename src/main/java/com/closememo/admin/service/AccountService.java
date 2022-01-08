package com.closememo.admin.service;

import com.closememo.admin.dao.AccountDAO;
import com.closememo.admin.dto.AccountDTO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

  private final AccountDAO accountDAO;

  public AccountService(AccountDAO accountDAO) {
    this.accountDAO = accountDAO;
  }

  public String createAccount(String username, String password) {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    String encodedPassword = passwordEncoder.encode(password);
    return accountDAO.createAccount(username, encodedPassword);
  }

  public AccountDTO getAccountByUsername(String username) {
    return accountDAO.getAccountByUsername(username)
        .orElseThrow(RuntimeException::new);
  }
}
