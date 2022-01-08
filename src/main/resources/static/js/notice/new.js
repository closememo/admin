(function ($) {
  'use strict';

  const $titleInput = $('#titleInput');
  const $contentInput = $('#contentInput');
  const $submitButton = $('#submitButton');

  $submitButton.on('click', function (event) {
    event.preventDefault();

    const title = $titleInput.val();
    const content = $contentInput.val();

    const data = {
      title, content
    }

    $.ajax({
      type: 'POST',
      url: '/admin/api/create-notice',
      contentType: 'application/json',
      data: JSON.stringify(data),
      success: function () {
        location.href = '/admin/notice';
      },
      error: function () {
        console.log('create notice error');
      }
    })
  });

})(jQuery);
