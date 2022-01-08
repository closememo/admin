(function ($) {
  'use strict';

  const $idInput = $('#idInput');
  const $titleInput = $('#titleInput');
  const $contentInput = $('#contentInput');
  const $submitButton = $('#submitButton');

  $submitButton.on('click', function (event) {
    event.preventDefault();

    const noticeId = $idInput.val();
    const title = $titleInput.val();
    const content = $contentInput.val();

    const data = {
      noticeId, title, content
    }

    $.ajax({
      type: 'POST',
      url: '/admin/api/update-notice',
      contentType: 'application/json',
      data: JSON.stringify(data),
      success: function () {
        location.href = '/admin/notice';
      },
      error: function () {
        console.log('update notice error');
      }
    })
  });

})(jQuery);
