package com.closememo.admin.infra.http.command.request;

import lombok.Getter;

@Getter
public class CommandUpdateNoticeRequest {

  private final String noticeId;
  private final String title;
  private final String content;

  public CommandUpdateNoticeRequest(String noticeId, String title, String content) {
    this.noticeId = noticeId;
    this.title = title;
    this.content = content;
  }
}
