package com.closememo.admin.infra.http.command.request;

import lombok.Getter;

@Getter
public class CommandDeleteNoticeRequest {

  private final String noticeId;

  public CommandDeleteNoticeRequest(String noticeId) {
    this.noticeId = noticeId;
  }
}
