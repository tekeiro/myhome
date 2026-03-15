package org.keirobm.myhome.mediamanager.domain.search.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class SearchResult {
    private SearchResultType type;
    private String choice;
    private String filename;
    private int seeds;
    private double size;
}
