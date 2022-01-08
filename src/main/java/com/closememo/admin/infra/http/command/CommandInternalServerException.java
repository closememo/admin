package com.closememo.admin.infra.http.command;

import com.closememo.admin.infra.exception.InternalServerException;

public class CommandInternalServerException extends InternalServerException {

  private static final long serialVersionUID = 6553126023728937975L;

  public CommandInternalServerException() {
  }

  public CommandInternalServerException(String message) {
    super(message);
  }
}
