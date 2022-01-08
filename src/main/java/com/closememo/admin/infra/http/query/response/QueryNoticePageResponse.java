package com.closememo.admin.infra.http.query.response;

import java.util.List;
import lombok.Getter;

@Getter
public class QueryNoticePageResponse {

  private List<QueryNoticeResponse> data;
  private long total;
  private int currentPage;
  private int limit;
  private boolean hasNext;
}
