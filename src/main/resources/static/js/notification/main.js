(function ($) {
  'use strict';

  const $confirmModal = $('#confirmModal');
  const $confirmModalDeleteButton = $($confirmModal.find('._delete-button')[0]);
  const $infoModal = $('#infoModal');

  function openModalWithTitleAndContent($modal, title, content) {
    $modal.find('.modal-title').text(title);
    $modal.find('.modal-body').text(content);
    $modal.modal('toggle');
  }

  $('button._delete-button').on('click', function (event) {
    const $element = $(event.target);
    const notificationId = $element.data('notificationId');

    $confirmModalDeleteButton.data('notificationId', notificationId);
    $confirmModal.modal('toggle');
  });

  $('button._activation-button').on('click', function (event) {
    const $element = $(event.target);
    const notificationId = $element.data('notificationId');
    const isActive = $element.data('isActive');

    const url = isActive
        ? '/admin/api/inactivate-notification'
        : '/admin/api/activate-notification';

    $.ajax({
      type: 'POST',
      url: url,
      contentType: 'application/json',
      data: JSON.stringify({notificationId}),
      success: function () {
        location.href = '/admin/notification';
      },
      error: function (jqXHR) {
        if (jqXHR.status === 409) {
          openModalWithTitleAndContent($infoModal, '에러', '공개 기간이 겹치는 알림이 있습니다.')
        } else {
          console.log('activate notice error');
        }
      }
    });
  });

  $confirmModalDeleteButton.on('click', function () {
    const notificationId = $confirmModalDeleteButton.data('notificationId');
    if (!notificationId) {
      return;
    }

    $confirmModal.modal('hide');
    $.ajax({
      type: 'POST',
      url: '/admin/api/delete-notification',
      contentType: 'application/json',
      data: JSON.stringify({notificationId}),
      success: function () {
        location.href = '/admin/notification';
      },
      error: function () {
        console.log('delete notice error');
      }
    });
  })

})(jQuery);
