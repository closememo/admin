package com.closememo.admin.infra.http.command.request;

import java.time.ZonedDateTime;
import lombok.Getter;

@Getter
public class CommandUpdateNotificationRequest {

  private final String notificationId;
  private final String title;
  private final String content;
  private final ZonedDateTime notifyStart;
  private final ZonedDateTime notifyEnd;

  public CommandUpdateNotificationRequest(String notificationId, String title, String content,
      ZonedDateTime notifyStart, ZonedDateTime notifyEnd) {
    this.notificationId = notificationId;
    this.title = title;
    this.content = content;
    this.notifyStart = notifyStart;
    this.notifyEnd = notifyEnd;
  }
}
