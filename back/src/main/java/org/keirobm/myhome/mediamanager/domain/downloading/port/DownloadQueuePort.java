package org.keirobm.myhome.mediamanager.domain.downloading.port;

import org.keirobm.myhome.mediamanager.domain.downloading.model.DownloadQueueItem;

public interface DownloadQueuePort {
    void register(DownloadQueueItem item);
}
