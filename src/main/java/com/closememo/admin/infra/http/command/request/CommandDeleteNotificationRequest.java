package com.closememo.admin.infra.http.command.request;

import lombok.Getter;

@Getter
public class CommandDeleteNotificationRequest {

  private final String notificationId;

  public CommandDeleteNotificationRequest(String notificationId) {
    this.notificationId = notificationId;
  }
}
