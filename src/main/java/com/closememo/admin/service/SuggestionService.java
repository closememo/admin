package com.closememo.admin.service;

import com.closememo.admin.dto.SuggestionDTO;
import com.closememo.admin.dto.shared.OffsetPage;
import com.closememo.admin.infra.http.command.CommandSuggestionClient;
import com.closememo.admin.infra.http.command.request.CommandChangeSuggestionStatusRequest;
import com.closememo.admin.infra.http.query.QuerySuggestionClient;
import com.closememo.admin.infra.http.query.response.QueryPageResponse;
import com.closememo.admin.infra.http.query.response.QuerySuggestionResponse;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

@Service
public class SuggestionService {

  private final CommandSuggestionClient commandSuggestionClient;
  private final QuerySuggestionClient querySuggestionClient;

  public SuggestionService(
      CommandSuggestionClient commandSuggestionClient,
      QuerySuggestionClient querySuggestionClient) {
    this.commandSuggestionClient = commandSuggestionClient;
    this.querySuggestionClient = querySuggestionClient;
  }

  public void changeSuggestionStatus(String suggestionId, String status) {
    CommandChangeSuggestionStatusRequest request =
        new CommandChangeSuggestionStatusRequest(suggestionId, status);
    commandSuggestionClient.changeSuggestionStatus(request);
  }

  public OffsetPage<SuggestionDTO> getSuggestions(int page, int limit, String status) {
    QueryPageResponse<QuerySuggestionResponse> response =
        querySuggestionClient.getSuggestions(page, limit, status);

    if (CollectionUtils.isEmpty(response.getData())) {
      return OffsetPage.empty();
    }

    int lastPage = (int) Math.ceil((double) response.getTotal() / response.getLimit());

    List<SuggestionDTO> notices = response.getData().stream()
        .map(SuggestionService::convert)
        .collect(Collectors.toList());

    return new OffsetPage<>(notices, response.getTotal(), response.getCurrentPage(), lastPage);
  }

  private static SuggestionDTO convert(QuerySuggestionResponse querySuggestionResponse) {
    return new SuggestionDTO(
        querySuggestionResponse.getId(),
        querySuggestionResponse.getWriterId(),
        querySuggestionResponse.getEmail(),
        querySuggestionResponse.getContent(),
        querySuggestionResponse.getCreatedAt(),
        querySuggestionResponse.getStatus()
    );
  }
}
