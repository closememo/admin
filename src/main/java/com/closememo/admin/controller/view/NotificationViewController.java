package com.closememo.admin.controller.view;

import com.closememo.admin.controller.shared.dto.OffsetPage;
import com.closememo.admin.dto.NotificationDTO;
import com.closememo.admin.service.NotificationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@ViewController
public class NotificationViewController {

  private final NotificationService notificationService;

  public NotificationViewController(
      NotificationService notificationService) {
    this.notificationService = notificationService;
  }

  @GetMapping("notification")
  public ModelAndView getNotifications(
      @RequestParam(defaultValue = "1") Integer page,
      @RequestParam(defaultValue = "10") Integer limit) {

    OffsetPage<NotificationDTO> notificationPage =
        notificationService.getNotifications(page, limit);
    NotificationDTO currentNotification = notificationService.getCurrentNotification();

    ModelAndView modelAndView = new ModelAndView("notification/main");
    modelAndView.addObject("page", notificationPage);
    modelAndView.addObject("currentNotification", currentNotification);
    return modelAndView;
  }

  @GetMapping("/notification/new")
  public String newNotification() {
    return "notification/new";
  }

  @GetMapping("/notification/update")
  public ModelAndView updateNotification(@RequestParam String notificationId) {
    NotificationDTO notification = notificationService.getNotification(notificationId);

    ModelAndView modelAndView = new ModelAndView("notification/update");
    modelAndView.addObject("notification", notification);
    return modelAndView;
  }
}
