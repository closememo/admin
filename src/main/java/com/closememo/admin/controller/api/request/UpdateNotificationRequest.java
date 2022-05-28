package com.closememo.admin.controller.api.request;

import com.closememo.admin.infra.helper.CustomZonedDateTimeDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.time.ZonedDateTime;
import lombok.Getter;

@Getter
public class UpdateNotificationRequest {

  private String notificationId;
  private String title;
  private String content;
  @JsonDeserialize(using = CustomZonedDateTimeDeserializer.class)
  private ZonedDateTime notifyStart;
  @JsonDeserialize(using = CustomZonedDateTimeDeserializer.class)
  private ZonedDateTime notifyEnd;
}
