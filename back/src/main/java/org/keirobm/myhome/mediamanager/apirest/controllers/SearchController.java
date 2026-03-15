package org.keirobm.myhome.mediamanager.apirest.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.keirobm.myhome.mediamanager.application.search.InitiateDownloadUseCase;
import org.keirobm.myhome.mediamanager.application.search.NewSearchUseCase;
import org.keirobm.myhome.mediamanager.application.watchlist.ListMediaLibraryUseCase;
import org.keirobm.myhome.mediamanager.domain.queue.model.AcceptDownloadRequest;
import org.keirobm.myhome.mediamanager.domain.queue.model.DownloadQueueItem;
import org.keirobm.myhome.mediamanager.domain.search.model.SearchResult;
import org.keirobm.myhome.mediamanager.domain.watchlist.model.Film;
import org.keirobm.myhome.mediamanager.domain.watchlist.model.TvShow;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/v1/api/search")
@RequiredArgsConstructor
public class SearchController {

    private final NewSearchUseCase newSearchUseCase;

    private final InitiateDownloadUseCase initiateDownloadUseCase;

    private final ListMediaLibraryUseCase listMediaLibraryUseCase;

    @GetMapping("")
    public List<SearchResult> search(@RequestParam("q") String queryTerm) {
        return this.newSearchUseCase.executeNewSearch(queryTerm).getResults();
    }
    
    @PostMapping("accept")
    public DownloadQueueItem acceptNewSearchResult(@RequestBody AcceptDownloadRequest request) {
        return this.initiateDownloadUseCase.execute(request);
    }

    @GetMapping("tvShows")
    public List<TvShow> findAllTvShows() {
        return this.listMediaLibraryUseCase.findAllTvShows();
    }

    @GetMapping("films")
    public List<Film> findAllFilms() {
        return this.listMediaLibraryUseCase.findAllFilms();
    }


}
