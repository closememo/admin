package com.closememo.admin.infra.http.command.request;

import lombok.Getter;

@Getter
public class CommandInactivateNotificationRequest {

  private final String notificationId;

  public CommandInactivateNotificationRequest(String notificationId) {
    this.notificationId = notificationId;
  }
}
