package com.closememo.admin.service;

import com.closememo.admin.infra.http.query.QueryAccountClient;
import com.closememo.admin.infra.http.query.QueryCategoryClient;
import com.closememo.admin.infra.http.query.request.QueryRefreshCategoryRequest;
import com.closememo.admin.infra.http.query.response.QueryAccountResponse;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

  private final QueryAccountClient queryAccountClient;
  private final QueryCategoryClient queryCategoryClient;

  public CategoryService(QueryAccountClient queryAccountClient,
      QueryCategoryClient queryCategoryClient) {
    this.queryAccountClient = queryAccountClient;
    this.queryCategoryClient = queryCategoryClient;
  }

  public void refreshCategoryByEmail(String email) {
    QueryAccountResponse accountResponse = queryAccountClient.getAccountByEmail(email);
    QueryRefreshCategoryRequest request = new QueryRefreshCategoryRequest(accountResponse.getId());
    queryCategoryClient.refreshCategory(request);
  }
}
