package com.closememo.admin.dto;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
public class NoticeDTO implements Serializable {

  private static final long serialVersionUID = 1627347318104772175L;

  private static final int PREVIEW_LIMIT = 150;
  private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

  private final String id;
  private final String title;
  private final String content;
  private final String preview;
  private final ZonedDateTime createdAt;
  private final ZonedDateTime updatedAt;

  public NoticeDTO(String id, String title, String content, ZonedDateTime createdAt,
      ZonedDateTime updatedAt) {
    this.id = id;
    this.title = title;
    this.content = content;
    this.preview = substringPreview(content);
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  private static String substringPreview(String plainText) {
    return StringUtils.length(plainText) <= NoticeDTO.PREVIEW_LIMIT
        ? plainText
        : plainText.substring(0, plainText.offsetByCodePoints(0, NoticeDTO.PREVIEW_LIMIT)) + "...";
  }

  public String getCreatedAt() {
    return createdAt.format(DATE_FORMAT);
  }

  public String getUpdatedAt() {
    return updatedAt.format(DATE_FORMAT);
  }
}
