package org.keirobm.myhome.mediamanager.domain.search.port;

import org.keirobm.myhome.mediamanager.domain.search.model.WatchlistItem;
import org.keirobm.myhome.shared.PageRequest;
import org.keirobm.myhome.shared.PageResult;

public interface WatchlistItemPort {
    PageResult<WatchlistItem> getAll(PageRequest pageRequest);
}
