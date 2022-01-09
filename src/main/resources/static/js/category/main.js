(function ($) {
  'use strict';

  const $refreshCategoryEmailInput = $('#refreshCategoryEmailInput');
  const $refreshCategorySubmit = $('#refreshCategorySubmit');

  const $modal = $('#modal');
  const $modalConfirm = $('#modalConfirm');

  function openModalWithTitleAndContent($modal, title, content) {
    $modal.find('.modal-title').text(title);
    $modal.find('.modal-body').text(content);
    $modal.modal('toggle');
  }

  $modalConfirm.on('click', function () {
    $modal.modal('hide');
  })

  $refreshCategorySubmit.on('click', function (event) {
    event.preventDefault();

    const email = $refreshCategoryEmailInput.val();

    const data = {
      email
    }

    $.ajax({
      type: 'POST',
      url: '/admin/api/refresh-category',
      contentType: 'application/json',
      data: JSON.stringify(data),
      success: function () {
        openModalWithTitleAndContent($modal, '새로고침', '새로고침이 정상적으로 완료되었습니다.');
        $refreshCategoryEmailInput.val('');
      },
      error: function () {
        openModalWithTitleAndContent($modal, '새로고침', '새로고침 도중 에러 발생');
      }
    })
  });
})(jQuery);
