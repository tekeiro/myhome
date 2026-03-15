package org.keirobm.myhome.mediamanager.application.search;

import java.util.List;

import org.keirobm.myhome.mediamanager.domain.clients.port.AmuleClientPort;
import org.keirobm.myhome.mediamanager.domain.search.model.Search;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class NewSearchUseCase {

    private final AmuleClientPort amuleClientPort;

    public Search executeNewSearch(String searchTerm) {
        final var searchResults = this.amuleClientPort.search(searchTerm);
        return Search.builder()
                .queryTerm(searchTerm)
                .results(searchResults)
                .build();
    } 

}
