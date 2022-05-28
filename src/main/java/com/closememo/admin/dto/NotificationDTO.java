package com.closememo.admin.dto;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import lombok.Getter;

@Getter
public class NotificationDTO implements Serializable {

  private static final long serialVersionUID = -5041041368050768740L;
  private static final DateTimeFormatter DATE_FORMAT =
      DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
  private static final DateTimeFormatter DATE_FORMAT_FOR_INPUT =
      DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

  private final Boolean exist;
  private final String id;
  private final String title;
  private final String content;
  private final ZonedDateTime createdAt;
  private final ZonedDateTime notifyStart;
  private final ZonedDateTime notifyEnd;
  private final Status status;

  public NotificationDTO(Boolean exist, String id, String title, String content,
      ZonedDateTime createdAt, ZonedDateTime notifyStart, ZonedDateTime notifyEnd,
      Status status) {
    this.exist = exist;
    this.id = id;
    this.title = title;
    this.content = content;
    this.createdAt = createdAt;
    this.notifyStart = notifyStart;
    this.notifyEnd = notifyEnd;
    this.status = status;
  }

  public enum Status {
    ACTIVE,
    INACTIVE
  }

  public boolean isActive() {
    return status.equals(Status.ACTIVE);
  }

  public String getCreatedAt() {
    return createdAt.format(DATE_FORMAT);
  }

  public String getNotifyStart() {
    return notifyStart.format(DATE_FORMAT);
  }

  public String getNotifyStartForInput() {
    return notifyStart.format(DATE_FORMAT_FOR_INPUT);
  }

  public String getNotifyEnd() {
    return notifyEnd.format(DATE_FORMAT);
  }

  public String getNotifyEndForInput() {
    return notifyEnd.format(DATE_FORMAT_FOR_INPUT);
  }

  public static NotificationDTO empty() {
    return new NotificationDTO(false, null, null, null, null, null, null, null);
  }
}
