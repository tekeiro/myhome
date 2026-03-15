package org.keirobm.myhome.mediamanager.domain.search.model;

import lombok.Builder;
import lombok.Data;

/**
 * It can be an episode of a tv show, or a film.
 * It is used to represent the items in the watchlist, 
 * and also the items in the search results.
 */
@Data
@Builder(toBuilder = true)
public class WatchlistItemNode {
    private String title;

    // --- For tv shows
    private int season;
    private int episode;
    // --- For tv shows

    private boolean watched;

    // --- downloading status
    private DownloadStatus downloadStatus;
    private Double downloadProgress; // 0.0 to 100.0
    private String filePath;
    // --- downloading status

}
