package com.closememo.admin.infra.http.query;

import com.closememo.admin.infra.exception.InternalServerException;

public class QueryInternalServerException extends InternalServerException {

  private static final long serialVersionUID = -6135204181170887839L;

  public QueryInternalServerException() {
  }

  public QueryInternalServerException(String message) {
    super(message);
  }
}
