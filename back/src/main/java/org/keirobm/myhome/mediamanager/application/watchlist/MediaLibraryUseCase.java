package org.keirobm.myhome.mediamanager.application.watchlist;

import org.keirobm.myhome.mediamanager.domain.watchlist.form.NewFilmRequest;
import org.keirobm.myhome.mediamanager.domain.watchlist.form.NewTvShowRequest;
import org.keirobm.myhome.mediamanager.domain.watchlist.model.Film;
import org.keirobm.myhome.mediamanager.domain.watchlist.model.TvShow;
import org.keirobm.myhome.mediamanager.domain.watchlist.port.MediaLibraryPort;
import org.keirobm.myhome.shared.PageRequest;
import org.keirobm.myhome.shared.PageResult;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class MediaLibraryUseCase {

    private final MediaLibraryPort mediaLibraryPort;

    public PageResult<Film> listFilms(PageRequest pageRequest) {
        return this.mediaLibraryPort.listFilms(pageRequest);
    }

    public PageResult<TvShow> listTvShows(PageRequest pageRequest) {
        return this.mediaLibraryPort.listTvShows(pageRequest);
    }

    public Film createFilm(NewFilmRequest request) {
        final var newFilm = Film.builder()
            .title(request.getTitle())
            .year(request.getYear())
            .folderPath("")
            .watched(false)
            .build();
        return this.mediaLibraryPort.createOrUpdate(newFilm);
    }

    public TvShow createTvShow(NewTvShowRequest request) {
        final var newTvShow = TvShow.builder()
            .title(request.getTitle())
            .year(request.getYear())
            .folderPath("")
            .build();
        return this.mediaLibraryPort.createOrUpdate(newTvShow);
    }

}
