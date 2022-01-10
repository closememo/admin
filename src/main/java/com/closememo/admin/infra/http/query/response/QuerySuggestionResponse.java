package com.closememo.admin.infra.http.query.response;

import java.time.ZonedDateTime;
import lombok.Getter;

@Getter
public class QuerySuggestionResponse {

  private String id;
  private String writerId;
  private String email;
  private String content;
  private ZonedDateTime createdAt;
  private String status;
}
