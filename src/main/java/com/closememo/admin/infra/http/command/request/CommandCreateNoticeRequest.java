package com.closememo.admin.infra.http.command.request;

import lombok.Getter;

@Getter
public class CommandCreateNoticeRequest {

  private final String title;
  private final String content;

  public CommandCreateNoticeRequest(String title, String content) {
    this.title = title;
    this.content = content;
  }
}
