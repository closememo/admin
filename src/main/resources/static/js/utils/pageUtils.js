(function ($, Handlebars, currentPageParam, lastPageParam) {
  'use strict';

  const currentPage = parseInt(currentPageParam);
  const lastPage = parseInt(lastPageParam);

  const $pageNavigation = $('#pageNavigation');

  const listItemTemplate = $('#listItemTemplate').html();
  const ellipsisItemTemplate = $('#ellipsisItemTemplate').html();

  $(function () {
    init();
  });

  function init() {
    makePageNavigation();
  }

  function makePageNavigation() {
    $pageNavigation.empty();

    if (lastPage <= 5) {
      for (let i = 0; i < lastPage; i++) {
        const active = currentPage === (i + 1);
        $pageNavigation.append(generatePageItem(i + 1, active));
      }
      return;
    }

    const {startNumber, activeItem} = function (currentPage, lastPage) {
      if (currentPage === 1) {
        return {startNumber: 1, activeItem: 0};
      } else if (currentPage < lastPage) {
        return {startNumber: currentPage - 1, activeItem: 1}
      } else {
        return {startNumber: lastPage - 2, activeItem: 2}
      }
    }(currentPage, lastPage);

    if (currentPage > 2) $pageNavigation.append(generatePageItem(1));
    if (currentPage > 3) $pageNavigation.append(ellipsisItemTemplate);
    for (let j = 0; j < 3; j++) {
      const active = activeItem === j;
      $pageNavigation.append(generatePageItem(startNumber + j, active));
    }
    if (currentPage < lastPage - 2) $pageNavigation.append(ellipsisItemTemplate);
    if (currentPage < lastPage - 1) $pageNavigation.append(generatePageItem(lastPage));
  }

  function generatePageItem(page, active) {
    const template = Handlebars.compile(listItemTemplate);
    return template({page, active});
  }

})(jQuery, Handlebars, currentPage, lastPage);
