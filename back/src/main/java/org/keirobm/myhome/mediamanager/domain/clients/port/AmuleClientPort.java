package org.keirobm.myhome.mediamanager.domain.clients.port;

import java.util.List;

import org.keirobm.myhome.mediamanager.domain.downloading.model.AcceptDownloadRequest;
import org.keirobm.myhome.mediamanager.domain.downloading.model.DownloadQueueItem;
import org.keirobm.myhome.mediamanager.domain.search.model.SearchResult;


public interface AmuleClientPort {
    List<SearchResult> search(String queryTerm);

    DownloadQueueItem initiateDownload(AcceptDownloadRequest acceptDownloadRequest);
}
