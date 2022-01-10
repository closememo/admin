package com.closememo.admin.dto;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
public class SuggestionDTO implements Serializable {

  private static final long serialVersionUID = 4991450467222377359L;

  private static final int PREVIEW_LIMIT = 150;
  private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

  private final String id;
  private final String writerId;
  private final String email;
  private final String content;
  private final String preview;
  private final ZonedDateTime createdAt;
  private final String status;

  public SuggestionDTO(String id, String writerId, String email, String content,
      ZonedDateTime createdAt, String status) {
    this.id = id;
    this.writerId = writerId;
    this.email = email;
    this.content = content;
    this.preview = substringPreview(content);
    this.createdAt = createdAt;
    this.status = status;
  }

  private static String substringPreview(String plainText) {
    return StringUtils.length(plainText) <= PREVIEW_LIMIT
        ? plainText
        : plainText.substring(0, plainText.offsetByCodePoints(0, PREVIEW_LIMIT)) + "...";
  }

  public String getCreatedAt() {
    return createdAt.format(DATE_FORMAT);
  }
}
