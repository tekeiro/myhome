package org.keirobm.myhome.mediamanager.application.search;


import org.keirobm.myhome.mediamanager.domain.clients.port.AmuleClientPort;
import org.keirobm.myhome.mediamanager.domain.queue.model.AcceptDownloadRequest;
import org.keirobm.myhome.mediamanager.domain.queue.model.DownloadQueueItem;
import org.keirobm.myhome.mediamanager.domain.queue.port.DownloadQueuePort;
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

    private final DownloadQueuePort downloadQueuePort;

    public DownloadQueueItem execute(AcceptDownloadRequest request) {
        final var mediaType = request.getSearchResultType();
        switch (mediaType) {
            case SearchResultType.FILM:
                return this.acceptDownloadRequestForFilm(request);
            case SearchResultType.TV_SHOW, SearchResultType.TV_SHOW_EPISODE, SearchResultType.TV_SHOW_SEASON:
                return this.acceptDownloadRequestForTvShow(request);
            default:
                log.warn("Unknown media type {} for download request", mediaType);
                return null;
        }
    }

    private DownloadQueueItem acceptDownloadRequestForTvShow(AcceptDownloadRequest request) {
        // Search or create tv show (by title, year, season and episode)
        final var tvShow =this.mediaLibraryPort.searchOrCreateTvShow(request.getTitle(), request.getYear(), request.getSeason(), request.getEpisode());
        // Upload to amule the search result choice
        final var dlItem = this.amuleClientPort.initiateDownload(request);
        dlItem.toBuilder()
            .filmOrTvShowId(tvShow.getId())
            .episode(request.getEpisode())
            .season(request.getSeason())
            .build();
        this.downloadQueuePort.register(dlItem);
        return dlItem;
    }

    private DownloadQueueItem acceptDownloadRequestForFilm(AcceptDownloadRequest request) {
        // Search or create film (by title and year)
        final var film = this.mediaLibraryPort.searchOrCreateFilm(request.getTitle(), request.getYear());
        // Upload to amule the search result choice
        final var dlItem = this.amuleClientPort.initiateDownload(request);
        dlItem.toBuilder()
            .filmOrTvShowId(film.getId())
            .build();
        this.downloadQueuePort.register(dlItem);
        return dlItem;
    }

}
