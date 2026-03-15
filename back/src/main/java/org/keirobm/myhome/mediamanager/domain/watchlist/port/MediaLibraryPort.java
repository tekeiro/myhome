package org.keirobm.myhome.mediamanager.domain.watchlist.port;

import java.util.List;
import java.util.Optional;

import org.keirobm.myhome.mediamanager.domain.watchlist.model.Film;
import org.keirobm.myhome.mediamanager.domain.watchlist.model.TvShow;
import org.keirobm.myhome.shared.PageRequest;
import org.keirobm.myhome.shared.PageResult;

public interface MediaLibraryPort {
    Film searchOrCreateFilm(String title, int year);
    TvShow searchOrCreateTvShow(String title, int year, int season, int episode);
    List<Film> findAllFilms();
    List<TvShow> findAllTvShows();
    PageResult<Film> listFilms(PageRequest pageRequest);
    PageResult<TvShow> listTvShows(PageRequest pageRequest);
    Film createOrUpdate(Film film);
    TvShow createOrUpdate(TvShow tvShow);
}
