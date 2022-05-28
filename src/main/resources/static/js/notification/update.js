(function ($) {
  'use strict';

  const $idInput = $('#idInput');
  const $titleInput = $('#titleInput');
  const $contentInput = $('#contentInput');
  const $notifyStartInput = $('#notifyStartInput');
  const $notifyEndInput = $('#notifyEndInput');
  const $submitButton = $('#submitButton');

  $submitButton.on('click', function (event) {
    event.preventDefault();

    const notificationId = $idInput.val();
    const title = $titleInput.val();
    const content = $contentInput.val();
    const notifyStart = $notifyStartInput.val();
    const notifyEnd = $notifyEndInput.val();

    const data = {
      notificationId, title, content, notifyStart, notifyEnd
    }

    $.ajax({
      type: 'POST',
      url: '/admin/api/update-notification',
      contentType: 'application/json',
      data: JSON.stringify(data),
      success: function () {
        location.href = '/admin/notification';
      },
      error: function () {
        console.log('create notification error');
      }
    })
  });

})(jQuery);
