package org.keirobm.myhome.mediamanager.application.search;


import org.keirobm.myhome.mediamanager.domain.clients.port.AmuleClientPort;
import org.keirobm.myhome.mediamanager.domain.downloading.model.AcceptDownloadRequest;
import org.keirobm.myhome.mediamanager.domain.search.model.SearchResultType;
import org.keirobm.myhome.mediamanager.domain.watchlist.port.MediaLibraryPort;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class InitiateDownloadUseCase {

    private final MediaLibraryPort mediaLibraryPort;

    private final AmuleClientPort amuleClientPort;

    public boolean execute(AcceptDownloadRequest request) {
        final var mediaType = request.getSearchResultType();
        switch (mediaType) {
            case SearchResultType.FILM:
                return this.acceptDownloadRequestForFilm(request);
            case SearchResultType.TV_SHOW, SearchResultType.TV_SHOW_EPISODE, SearchResultType.TV_SHOW_SEASON:
                return this.acceptDownloadRequestForTvShow(request);
            default:
                log.warn("Unknown media type {} for download request", mediaType);
                return false;
        }
    }

    private boolean acceptDownloadRequestForTvShow(AcceptDownloadRequest request) {
        // Search or create tv show (by title, year, season and episode)
        this.mediaLibraryPort.searchOrCreateTvShow(request.getTitle(), request.getYear(), request.getSeason(), request.getEpisode());
        // Upload to amule the search result choice
        this.amuleClientPort.initiateDownload(request);
        return true;
    }

    private boolean acceptDownloadRequestForFilm(AcceptDownloadRequest request) {
        // Search or create film (by title and year)
        this.mediaLibraryPort.searchOrCreateFilm(request.getTitle(), request.getYear());
        // Upload to amule the search result choice
        this.amuleClientPort.initiateDownload(request);
        return true;
    }

}
