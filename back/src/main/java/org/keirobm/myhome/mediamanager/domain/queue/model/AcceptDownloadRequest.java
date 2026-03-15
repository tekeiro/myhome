package org.keirobm.myhome.mediamanager.domain.queue.model;

import org.keirobm.myhome.mediamanager.domain.search.model.SearchResult;
import org.keirobm.myhome.mediamanager.domain.search.model.SearchResultType;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class AcceptDownloadRequest {
    private SearchResult searchResult;
    private SearchResultType searchResultType;
    private String title;
    private int year;
    private int season;
    private int episode;
}
