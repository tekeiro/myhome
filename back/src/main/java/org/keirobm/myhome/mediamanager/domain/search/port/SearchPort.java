package org.keirobm.myhome.mediamanager.domain.search.port;

import org.keirobm.myhome.mediamanager.domain.search.model.SearchResult;

public interface SearchPort {
    void linkToDlItem(SearchResult searchResult);
    
}
