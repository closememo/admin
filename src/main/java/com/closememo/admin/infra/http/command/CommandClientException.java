package com.closememo.admin.infra.http.command;

import com.closememo.admin.infra.exception.InternalServerException;

public class CommandClientException extends InternalServerException {

  private static final long serialVersionUID = -3176834246284768706L;

  public CommandClientException() {
  }

  public CommandClientException(String message) {
    super(message);
  }
}
