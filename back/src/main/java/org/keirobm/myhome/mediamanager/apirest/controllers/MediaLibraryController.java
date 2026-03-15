package org.keirobm.myhome.mediamanager.apirest.controllers;

import org.keirobm.myhome.mediamanager.application.watchlist.MediaLibraryUseCase;
import org.keirobm.myhome.mediamanager.domain.watchlist.form.NewFilmRequest;
import org.keirobm.myhome.mediamanager.domain.watchlist.form.NewTvShowRequest;
import org.keirobm.myhome.mediamanager.domain.watchlist.model.Film;
import org.keirobm.myhome.mediamanager.domain.watchlist.model.TvShow;
import org.keirobm.myhome.shared.PageRequest;
import org.keirobm.myhome.shared.PageResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping(value = "v1/api/media", consumes = "application/json", produces = "application/json")
@RequiredArgsConstructor
public class MediaLibraryController {

    private final MediaLibraryUseCase mediaLibraryUseCase;

    @GetMapping("films")
    public PageResult<Film> listFilms(@RequestParam PageRequest pageRequest) {
        return this.mediaLibraryUseCase.listFilms(pageRequest);
    }

    @PostMapping("films")
    public Film createNewFilm(@RequestBody NewFilmRequest request) {
        return this.mediaLibraryUseCase.createFilm(request);
    }
    

    @GetMapping("tvShows")
    public PageResult<TvShow> listTvShows(@RequestParam PageRequest pageRequest) {
        return this.mediaLibraryUseCase.listTvShows(pageRequest);
    }
    
    @PostMapping("tvShows")
    public TvShow createNewTvShow(@RequestBody NewTvShowRequest request) {
        return this.mediaLibraryUseCase.createTvShow(request);
    }

}
