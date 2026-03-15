package org.keirobm.myhome.mediamanager.apirest.controllers;

import java.util.List;

import org.keirobm.myhome.mediamanager.domain.search.model.WatchlistItem;
import org.keirobm.myhome.mediamanager.domain.search.port.WatchlistItemPort;
import org.keirobm.myhome.shared.PageRequest;
import org.keirobm.myhome.shared.PageResult;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/v1/api/watchlist")
@RequiredArgsConstructor
public class WatchlistItemController {

    private final WatchlistItemPort watchlistItemPort;

    @GetMapping("items")
    public PageResult<WatchlistItem> listWatchlistItems(@RequestParam PageRequest pageRequest) {
        return this.watchlistItemPort.getAll(pageRequest);
    }
    

}
