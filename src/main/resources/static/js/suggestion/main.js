(function ($, currentPageParam) {
  'use strict';

  const currentPage = parseInt(currentPageParam);

  $('button._set-status-button').on('click', function (event) {
    const $element = $(event.target);
    const suggestionId = $element.data('suggestionId');
    const status = $element.data('status');
    if (!suggestionId) {
      return;
    }
    changeSuggestionStatus(suggestionId, status);
  });

  function changeSuggestionStatus(suggestionId, status) {
    $.ajax({
      type: 'POST',
      url: '/admin/api/change-suggestion-status',
      contentType: 'application/json',
      data: JSON.stringify({suggestionId, status}),
      success: function () {
        location.href = '/admin/suggestion?page=' + currentPage;
      },
      error: function () {
        console.log('delete notice error');
      }
    })
  }
})(jQuery, currentPage);
