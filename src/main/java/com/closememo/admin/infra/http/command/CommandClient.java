package com.closememo.admin.infra.http.command;

import com.closememo.admin.infra.exception.ResourceAlreadyExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;

public abstract class CommandClient {

  protected CommandRestTemplateProxy restTemplate;

  public CommandClient(CommandRestTemplateProxy restTemplate) {
    this.restTemplate = restTemplate;
  }

  protected void validateResponse(@NonNull ResponseEntity<?> response, String errorMessage) {
    if (response.getStatusCode().equals(HttpStatus.CONFLICT)) {
      throw new ResourceAlreadyExistException(errorMessage);
    }

    if (response.getStatusCode().is5xxServerError()) {
      throw new CommandInternalServerException(errorMessage);
    }

    if (response.getStatusCode().isError()) {
      throw new CommandClientException(errorMessage);
    }
  }
}
