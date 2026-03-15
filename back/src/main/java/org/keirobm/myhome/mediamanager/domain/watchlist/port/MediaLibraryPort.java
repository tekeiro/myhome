package org.keirobm.myhome.mediamanager.domain.watchlist.port;

import java.util.Optional;

import org.keirobm.myhome.mediamanager.domain.watchlist.model.Film;
import org.keirobm.myhome.mediamanager.domain.watchlist.model.TvShow;

public interface MediaLibraryPort {
    Film searchOrCreateFilm(String title, int year);
    TvShow searchOrCreateTvShow(String title, int year, int season, int episode);

}
