package com.closememo.admin.controller.view;

import com.closememo.admin.controller.shared.dto.OffsetPage;
import com.closememo.admin.dto.NoticeDTO;
import com.closememo.admin.service.NoticeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@ViewController
public class NoticeViewController {

  private final NoticeService noticeService;

  public NoticeViewController(NoticeService noticeService) {
    this.noticeService = noticeService;
  }

  @GetMapping("/notice")
  public ModelAndView noticeList(
      @RequestParam(defaultValue = "1") Integer page,
      @RequestParam(defaultValue = "10") Integer limit) {
    OffsetPage<NoticeDTO> noticePage = noticeService.getNotices(page, limit);

    ModelAndView modelAndView = new ModelAndView("notice/main");
    modelAndView.addObject("page", noticePage);
    return modelAndView;
  }

  @GetMapping("/notice/new")
  public String newNotice() {
    return "notice/new";
  }

  @GetMapping("/notice/update")
  public ModelAndView updateNotice(@RequestParam String noticeId) {
    NoticeDTO notice = noticeService.getNotice(noticeId);

    ModelAndView modelAndView = new ModelAndView("notice/update");
    modelAndView.addObject("notice", notice);
    return modelAndView;
  }
}
