package com.closememo.admin.controller.api;

import com.closememo.admin.controller.api.request.CreateNoticeRequest;
import com.closememo.admin.controller.api.request.DeleteNoticeRequest;
import com.closememo.admin.controller.api.request.UpdateNoticeRequest;
import com.closememo.admin.controller.shared.dto.OffsetPage;
import com.closememo.admin.dto.NoticeDTO;
import com.closememo.admin.service.NoticeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@AdminApiController
public class NoticeApiController {

  private final NoticeService noticeService;

  public NoticeApiController(NoticeService noticeService) {
    this.noticeService = noticeService;
  }

  @PostMapping("/create-notice")
  public void createNotice(@RequestBody CreateNoticeRequest request) {
    noticeService.createNotice(request.getTitle(), request.getContent());
  }

  @PostMapping("/update-notice")
  public void updateNotice(@RequestBody UpdateNoticeRequest request) {
    noticeService.updateNotice(request.getNoticeId(), request.getTitle(), request.getContent());
  }

  @PostMapping("/delete-notice")
  public void deleteNotice(@RequestBody DeleteNoticeRequest request) {
    noticeService.deleteNotice(request.getNoticeId());
  }

  @GetMapping("/notices")
  public OffsetPage<NoticeDTO> getNotices(
      @RequestParam(defaultValue = "1") Integer page,
      @RequestParam(defaultValue = "10") Integer limit) {
    return noticeService.getNotices(page, limit);
  }

  @GetMapping("/notices/{noticeId}")
  public NoticeDTO getNotice(@PathVariable String noticeId) {
    return noticeService.getNotice(noticeId);
  }
}
