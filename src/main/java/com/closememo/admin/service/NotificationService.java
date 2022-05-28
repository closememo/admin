package com.closememo.admin.service;

import com.closememo.admin.controller.shared.dto.OffsetPage;
import com.closememo.admin.dto.NotificationDTO;
import com.closememo.admin.dto.NotificationDTO.Status;
import com.closememo.admin.infra.http.command.CommandNotificationClient;
import com.closememo.admin.infra.http.command.request.CommandActivateNotificationRequest;
import com.closememo.admin.infra.http.command.request.CommandCreateNotificationRequest;
import com.closememo.admin.infra.http.command.request.CommandDeleteNotificationRequest;
import com.closememo.admin.infra.http.command.request.CommandInactivateNotificationRequest;
import com.closememo.admin.infra.http.command.request.CommandUpdateNotificationRequest;
import com.closememo.admin.infra.http.query.QueryNotificationClient;
import com.closememo.admin.infra.http.query.response.QueryNotificationResponse;
import com.closememo.admin.infra.http.query.response.QueryPageResponse;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

  private final CommandNotificationClient commandNotificationClient;
  private final QueryNotificationClient queryNotificationClient;

  public NotificationService(
      CommandNotificationClient commandNotificationClient,
      QueryNotificationClient queryNotificationClient) {
    this.commandNotificationClient = commandNotificationClient;
    this.queryNotificationClient = queryNotificationClient;
  }

  public void createNotification(String title, String content,
      ZonedDateTime notifyStart, ZonedDateTime notifyEnd) {

    CommandCreateNotificationRequest request = new CommandCreateNotificationRequest(title, content,
        notifyStart, notifyEnd);
    commandNotificationClient.createNotification(request);
  }

  public void updateNotification(String notificationId, String title, String content,
      ZonedDateTime notifyStart, ZonedDateTime notifyEnd) {

    CommandUpdateNotificationRequest request = new CommandUpdateNotificationRequest(notificationId,
        title, content, notifyStart, notifyEnd);
    commandNotificationClient.updateNotification(request);
  }

  public void activateNotification(String notificationId) {
    CommandActivateNotificationRequest request = new CommandActivateNotificationRequest(
        notificationId);
    commandNotificationClient.activateNotification(request);
  }

  public void inactivateNotification(String notificationId) {
    CommandInactivateNotificationRequest request = new CommandInactivateNotificationRequest(
        notificationId);
    commandNotificationClient.inactivateNotification(request);
  }

  public void deleteNotification(String notificationId) {
    CommandDeleteNotificationRequest request = new CommandDeleteNotificationRequest(notificationId);
    commandNotificationClient.deleteNotification(request);
  }

  public OffsetPage<NotificationDTO> getNotifications(int page, int limit) {
    QueryPageResponse<QueryNotificationResponse> response = queryNotificationClient
        .getNotifications(page, limit);

    if (CollectionUtils.isEmpty(response.getData())) {
      return OffsetPage.empty();
    }

    int lastPage = (int) Math.ceil((double) response.getTotal() / response.getLimit());

    List<NotificationDTO> notifications = response.getData().stream()
        .map(NotificationService::convert)
        .collect(Collectors.toList());

    return new OffsetPage<>(notifications, response.getTotal(),
        response.getCurrentPage(), lastPage);
  }

  public NotificationDTO getNotification(String notificationId) {
    QueryNotificationResponse response = queryNotificationClient.getNotification(notificationId);
    return convert(response);
  }

  public NotificationDTO getCurrentNotification() {
    QueryNotificationResponse response = queryNotificationClient.getCurrentNotification();
    return convert(response);
  }

  private static NotificationDTO convert(QueryNotificationResponse response) {
    if (!response.getExist()) {
      return NotificationDTO.empty();
    }
    return new NotificationDTO(
        response.getExist(),
        response.getId(),
        response.getTitle(),
        response.getContent(),
        response.getCreatedAt(),
        response.getNotifyStart(),
        response.getNotifyEnd(),
        Status.valueOf(response.getStatus())
    );
  }
}
