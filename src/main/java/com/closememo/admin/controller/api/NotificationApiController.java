package com.closememo.admin.controller.api;

import com.closememo.admin.controller.api.request.ActivateNotificationRequest;
import com.closememo.admin.controller.api.request.CreateNotificationRequest;
import com.closememo.admin.controller.api.request.DeleteNotificationRequest;
import com.closememo.admin.controller.api.request.InactivateNotificationRequest;
import com.closememo.admin.controller.api.request.UpdateNotificationRequest;
import com.closememo.admin.service.NotificationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@AdminApiController
public class NotificationApiController {

  private final NotificationService notificationService;

  public NotificationApiController(
      NotificationService notificationService) {
    this.notificationService = notificationService;
  }

  @PostMapping("/create-notification")
  public void createNotification(@RequestBody CreateNotificationRequest request) {
    notificationService.createNotification(request.getTitle(), request.getContent(),
        request.getNotifyStart(), request.getNotifyEnd());
  }

  @PostMapping("/update-notification")
  public void updateNotification(@RequestBody UpdateNotificationRequest request) {
    notificationService.updateNotification(request.getNotificationId(), request.getTitle(),
        request.getContent(), request.getNotifyStart(), request.getNotifyEnd());
  }

  @PostMapping("/activate-notification")
  public void updateNotification(@RequestBody ActivateNotificationRequest request) {
    notificationService.activateNotification(request.getNotificationId());
  }

  @PostMapping("/inactivate-notification")
  public void updateNotification(@RequestBody InactivateNotificationRequest request) {
    notificationService.inactivateNotification(request.getNotificationId());
  }

  @PostMapping("/delete-notification")
  public void deleteNotification(@RequestBody DeleteNotificationRequest request) {
    notificationService.deleteNotification(request.getNotificationId());
  }
}
