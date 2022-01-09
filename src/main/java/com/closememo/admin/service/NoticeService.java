package com.closememo.admin.service;

import com.closememo.admin.dto.NoticeDTO;
import com.closememo.admin.dto.shared.OffsetPage;
import com.closememo.admin.infra.http.command.CommandClient;
import com.closememo.admin.infra.http.command.request.CommandCreateNoticeRequest;
import com.closememo.admin.infra.http.command.request.CommandDeleteNoticeRequest;
import com.closememo.admin.infra.http.command.request.CommandUpdateNoticeRequest;
import com.closememo.admin.infra.http.query.QueryNoticeClient;
import com.closememo.admin.infra.http.query.response.QueryNoticePageResponse;
import com.closememo.admin.infra.http.query.response.QueryNoticeResponse;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

@Service
public class NoticeService {

  private final CommandClient commandClient;
  private final QueryNoticeClient queryNoticeClient;

  public NoticeService(CommandClient commandClient,
      QueryNoticeClient queryNoticeClient) {
    this.commandClient = commandClient;
    this.queryNoticeClient = queryNoticeClient;
  }

  public Boolean createNotice(String title, String content) {
    CommandCreateNoticeRequest request = new CommandCreateNoticeRequest(title, content);
    commandClient.createNotice(request);
    return true;
  }

  public Boolean updateNotice(String noticeId, String title, String content) {
    CommandUpdateNoticeRequest request = new CommandUpdateNoticeRequest(noticeId, title, content);
    commandClient.updateNotice(request);
    return true;
  }

  public Boolean deleteNotice(String noticeId) {
    CommandDeleteNoticeRequest request = new CommandDeleteNoticeRequest(noticeId);
    commandClient.deleteNotice(request);
    return true;
  }

  public OffsetPage<NoticeDTO> getNotices(int page, int limit) {
    QueryNoticePageResponse response = queryNoticeClient.getNotices(page, limit);

    if (CollectionUtils.isEmpty(response.getData())) {
      return OffsetPage.empty();
    }

    int lastPage = (int) Math.ceil((double) response.getTotal() / response.getLimit());

    List<NoticeDTO> notices = response.getData().stream()
        .map(NoticeService::convert)
        .collect(Collectors.toList());

    return new OffsetPage<>(notices, response.getTotal(), response.getCurrentPage(), lastPage);
  }

  public NoticeDTO getNotice(String noticeId) {
    QueryNoticeResponse response = queryNoticeClient.getNotice(noticeId);
    return convert(response);
  }

  private static NoticeDTO convert(QueryNoticeResponse queryNoticeResponse) {
    return new NoticeDTO(
        queryNoticeResponse.getId(),
        queryNoticeResponse.getTitle(),
        queryNoticeResponse.getContent(),
        queryNoticeResponse.getCreatedAt(),
        queryNoticeResponse.getUpdatedAt()
    );
  }
}
