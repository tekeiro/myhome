package org.keirobm.myhome.mediamanager.domain.downloading.model;

import org.keirobm.myhome.mediamanager.domain.search.model.SearchResultType;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class DownloadQueueItem {
    private MediaCategory contentType;
    private SearchResultType searchResultType;
    private String hash;
    private String filename;
    private double percentage;
}
