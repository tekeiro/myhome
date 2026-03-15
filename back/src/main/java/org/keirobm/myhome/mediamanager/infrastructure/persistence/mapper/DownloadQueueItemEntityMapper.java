package org.keirobm.myhome.mediamanager.infrastructure.persistence.mapper;

import org.keirobm.myhome.mediamanager.domain.queue.model.DownloadQueueItem;
import org.keirobm.myhome.mediamanager.infrastructure.persistence.entities.DownloadQueueItemEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DownloadQueueItemEntityMapper {
    DownloadQueueItemEntity toEntity(DownloadQueueItem item);
    DownloadQueueItem toDomain(DownloadQueueItemEntity entity);
}
