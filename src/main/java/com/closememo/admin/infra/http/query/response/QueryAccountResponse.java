package com.closememo.admin.infra.http.query.response;

import java.util.Set;
import lombok.Getter;

@Getter
public class QueryAccountResponse {

  private String id;
  private String email;
  private Set<String> roles;
}
