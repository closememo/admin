package com.closememo.admin.controller.shared.errorhandler;

import com.closememo.admin.infra.exception.BusinessException;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(basePackages = {
    "com.closememo.admin.controller"
})
public class RestControllerExceptionHandler {

  @ExceptionHandler({
      BusinessException.class
  })
  protected ResponseEntity<ErrorResponse> handler(BusinessException exception) {
    if (exception.isNecessaryToLog()) {
      String msg = Optional.ofNullable(exception.getMessage()).orElse(exception.getClass().getSimpleName());
      log.error(msg, exception);
    }

    Error error = new Error(exception.getClass().getSimpleName(), exception.getMessage());
    return new ResponseEntity<>(new ErrorResponse(error), new HttpHeaders(), exception.getHttpStatus());
  }
}
