package com.closememo.admin.infra.http.query.request;

import lombok.Getter;

@Getter
public class QueryRefreshCategoryRequest {

  private final String accountId;

  public QueryRefreshCategoryRequest(String accountId) {
    this.accountId = accountId;
  }
}
