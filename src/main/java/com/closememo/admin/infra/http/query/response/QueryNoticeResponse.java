package com.closememo.admin.infra.http.query.response;

import java.time.ZonedDateTime;
import lombok.Getter;

@Getter
public class QueryNoticeResponse {

  private String id;
  private String title;
  private String content;
  private ZonedDateTime createdAt;
  private ZonedDateTime updatedAt;
}
