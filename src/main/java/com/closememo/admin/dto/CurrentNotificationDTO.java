package com.closememo.admin.dto;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import lombok.Getter;

@Getter
public class CurrentNotificationDTO implements Serializable {

  private static final long serialVersionUID = 5516768928214841616L;
  private static final DateTimeFormatter DATE_FORMAT =
      DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

  private final String id;
  private final String title;
  private final String content;
  private final ZonedDateTime notifyStart;
  private final ZonedDateTime notifyEnd;

  public CurrentNotificationDTO(String id, String title, String content,
      ZonedDateTime notifyStart, ZonedDateTime notifyEnd) {
    this.id = id;
    this.title = title;
    this.content = content;
    this.notifyStart = notifyStart;
    this.notifyEnd = notifyEnd;
  }

  public String getNotifyStart() {
    return notifyStart.format(DATE_FORMAT);
  }

  public String getNotifyEnd() {
    return notifyEnd.format(DATE_FORMAT);
  }
}
