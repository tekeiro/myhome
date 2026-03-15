package org.keirobm.myhome.mediamanager.domain.downloading.model;

import org.keirobm.myhome.mediamanager.domain.search.model.SearchResultType;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class DownloadQueueItem {
    private Long id;
    private MediaCategory contentType;
    private SearchResultType searchResultType;
    private String hash;
    private String filename;
    private double percentage;
    private Long filmOrTvShowId;
    private Integer season;
    private Integer episode;
}
