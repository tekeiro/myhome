package org.keirobm.myhome.mediamanager.application.watchlist;

import java.util.List;

import org.keirobm.myhome.mediamanager.domain.watchlist.model.Film;
import org.keirobm.myhome.mediamanager.domain.watchlist.model.TvShow;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class SearchByNameMediaLibraryUseCase {

    public List<Film> searchFilmsByName(String name) {
        return List.of();
    }

    public List<TvShow> searchTvShowsByName(String name) {
        return List.of();
    }


}
