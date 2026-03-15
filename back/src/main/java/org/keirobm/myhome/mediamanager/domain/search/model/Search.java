package org.keirobm.myhome.mediamanager.domain.search.model;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Search {
    private String queryTerm;
    private List<SearchResult> results;
}
