package org.keirobm.myhome.mediamanager.infrastructure.persistence.adapter;

import java.util.List;
import java.util.stream.StreamSupport;

import org.keirobm.myhome.mediamanager.domain.queue.model.DownloadQueueItem;
import org.keirobm.myhome.mediamanager.domain.queue.port.DownloadQueuePort;
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

    @Override
    public List<DownloadQueueItem> findAll() {
        return StreamSupport.stream(this.repository.findAll().spliterator(), false)
            .map(this.mapper::toDomain)
            .toList();
    }

    @Override
    public void update(DownloadQueueItem item) {
        final var entity = this.mapper.toEntity(item);
        this.repository.save(entity);
    }

}
