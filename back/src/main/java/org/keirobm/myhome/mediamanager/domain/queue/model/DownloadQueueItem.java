package org.keirobm.myhome.mediamanager.domain.queue.model;

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

    private double mbSpeed;
    private int leachers;
    private int seeders;

    public void markAsCompleted(boolean completed) {
        if (completed) {
            this.percentage = 100.0;
        }
    }
    public boolean isCompleted() {
        return this.percentage == 100.0;
    }
}
