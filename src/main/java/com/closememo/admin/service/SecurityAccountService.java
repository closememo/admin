package com.closememo.admin.service;

import com.closememo.admin.dao.AccountDAO;
import com.closememo.admin.dto.AccountDTO;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityAccountService implements UserDetailsService {

  private final AccountDAO accountDAO;

  public SecurityAccountService(AccountDAO accountDAO) {
    this.accountDAO = accountDAO;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    AccountDTO accountDTO = accountDAO.getAccountByUsername(username)
        .orElseThrow(RuntimeException::new);

    List<GrantedAuthority> authorities = accountDTO.getRoles().stream()
        .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
        .collect(Collectors.toList());

    return new User(accountDTO.getUsername(), accountDTO.getPassword(), authorities);
  }
}
