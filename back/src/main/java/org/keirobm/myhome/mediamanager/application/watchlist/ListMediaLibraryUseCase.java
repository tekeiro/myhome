package org.keirobm.myhome.mediamanager.application.watchlist;

import java.util.List;

import org.keirobm.myhome.mediamanager.domain.watchlist.model.Film;
import org.keirobm.myhome.mediamanager.domain.watchlist.model.TvShow;
import org.keirobm.myhome.mediamanager.domain.watchlist.port.MediaLibraryPort;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * List media library but with no pagination. 
 * Useful for selects.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class ListMediaLibraryUseCase {

    private final MediaLibraryPort mediaLibraryPort;

    public List<Film> findAllFilms() {
        return mediaLibraryPort.findAllFilms();
    }

    public List<TvShow> findAllTvShows() {
        return mediaLibraryPort.findAllTvShows();
    }

}
