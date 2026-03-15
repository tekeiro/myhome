package org.keirobm.myhome.mediamanager.infrastructure.persistence.adapter;

import org.keirobm.myhome.mediamanager.domain.downloading.model.DownloadQueueItem;
import org.keirobm.myhome.mediamanager.domain.downloading.port.DownloadQueuePort;
import org.keirobm.myhome.mediamanager.infrastructure.persistence.mapper.DownloadQueueItemEntityMapper;
import org.keirobm.myhome.mediamanager.infrastructure.persistence.repositories.DownloadQueueItemRepository;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class DownloadQueueAdapter implements DownloadQueuePort{

    private final DownloadQueueItemRepository repository;

    private final DownloadQueueItemEntityMapper mapper;

    @Override
    public void register(DownloadQueueItem item) {
        final var entity = this.mapper.toEntity(item);
        this.repository.save(entity);
    }

}
