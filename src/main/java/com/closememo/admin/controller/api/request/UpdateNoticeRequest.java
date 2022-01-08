package com.closememo.admin.controller.api.request;

import lombok.Getter;

@Getter
public class UpdateNoticeRequest {

  private String noticeId;
  private String title;
  private String content;
}
