package org.keirobm.myhome.mediamanager.domain.queue.port;

import java.util.List;

import org.keirobm.myhome.mediamanager.domain.queue.model.DownloadQueueItem;

public interface DownloadQueuePort {
    void register(DownloadQueueItem item);
    List<DownloadQueueItem> findAll();
    void update(DownloadQueueItem item);
}
