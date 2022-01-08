package com.closememo.admin.infra.http.query;

import com.closememo.admin.infra.exception.InternalServerException;

public class QueryClientException extends InternalServerException {

  private static final long serialVersionUID = 6825880375971099137L;

  public QueryClientException() {
  }

  public QueryClientException(String message) {
    super(message);
  }
}
