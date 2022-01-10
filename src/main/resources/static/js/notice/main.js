(function ($) {
  'use strict';

  const $confirmModal = $('#confirmModal');
  const $modalConfirm = $('#modalConfirm');

  $('button._delete-button').on('click', function (event) {
    const $element = $(event.target);
    const noticeId = $element.data('noticeId');

    $modalConfirm.data('noticeId', noticeId);
    $confirmModal.modal('toggle');
  });

  $modalConfirm.on('click', function () {
    const noticeId = $modalConfirm.data('noticeId');
    if (!noticeId) {
      return;
    }

    $confirmModal.modal('hide');
    $.ajax({
      type: 'POST',
      url: '/admin/api/delete-notice',
      contentType: 'application/json',
      data: JSON.stringify({noticeId}),
      success: function () {
        location.href = '/admin/notice';
      },
      error: function () {
        console.log('delete notice error');
      }
    });
  })

})(jQuery);
