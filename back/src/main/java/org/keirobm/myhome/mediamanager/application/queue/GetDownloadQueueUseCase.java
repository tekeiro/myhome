package org.keirobm.myhome.mediamanager.application.queue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.keirobm.myhome.mediamanager.domain.clients.port.AmuleClientPort;
import org.keirobm.myhome.mediamanager.domain.queue.model.DownloadQueueItem;
import org.keirobm.myhome.mediamanager.domain.queue.port.DownloadQueuePort;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetDownloadQueueUseCase {

    private final DownloadQueuePort downloadQueuePort;

    private final AmuleClientPort amuleClientPort;

    public List<DownloadQueueItem> getDownloadQueue() {
        final var dbDlQueue = this.downloadQueuePort.findAll();
        final var dlQueue = this.amuleClientPort.getDownloadQueue();
        final var dlQueueByHash = dlQueue.stream().collect(Collectors.toMap(DownloadQueueItem::getHash, Function.identity(),
            (item1, item2) -> item2, HashMap::new));
        final var dbDlQueueByHash = dbDlQueue.stream().collect(Collectors.toMap(DownloadQueueItem::getHash, Function.identity(),
            (item1, item2) -> item2, HashMap::new));
        dlQueue.forEach(dlQueueItem -> {
            if (dbDlQueueByHash.containsKey(dlQueueItem.getHash())) {
                final var toUpdateQueueItem = dbDlQueueByHash.get(dlQueueItem.getHash());
                final var dblItemUpdated = toUpdateQueueItem.toBuilder()
                    .percentage(dlQueueItem.getPercentage())
                    .mbSpeed(dlQueueItem.getMbSpeed())
                    .leachers(dlQueueItem.getLeachers())
                    .seeders(dlQueueItem.getSeeders())
                    .build();
                this.downloadQueuePort.update(dblItemUpdated);
                dbDlQueueByHash.put(dlQueueItem.getHash(), dblItemUpdated);
            }
        });
        dbDlQueue.forEach(dbQueueItem -> {
            // If a queue item is not in amule download queue, its because it has finished
            if (!dlQueueByHash.containsKey(dbQueueItem.getHash())) {
                final var updatedItem = dbQueueItem.toBuilder()
                    .percentage(100.0)
                    .build();
                this.downloadQueuePort.update(updatedItem);
                dbDlQueueByHash.put(dbQueueItem.getHash(), updatedItem);
            }
        });
        return new ArrayList<>(dbDlQueueByHash.values());
    }

}
