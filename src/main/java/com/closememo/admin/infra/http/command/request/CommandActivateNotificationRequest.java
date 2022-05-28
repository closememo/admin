package com.closememo.admin.infra.http.command.request;

import lombok.Getter;

@Getter
public class CommandActivateNotificationRequest {

  private final String notificationId;

  public CommandActivateNotificationRequest(String notificationId) {
    this.notificationId = notificationId;
  }
}
