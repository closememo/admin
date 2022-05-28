package com.closememo.admin.controller.shared.errorhandler;

import lombok.Getter;

@Getter
public class ErrorResponse {

  private final Error error;

  public ErrorResponse(Error error) {
    this.error = error;
  }
}
