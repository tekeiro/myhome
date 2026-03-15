package org.keirobm.myhome.mediamanager.infrastructure.persistence.repositories;

import org.keirobm.myhome.mediamanager.infrastructure.persistence.entities.DownloadQueueItemEntity;
import org.keirobm.myhome.shared.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository 
public interface DownloadQueueItemRepository extends BaseRepository<DownloadQueueItemEntity, Long> {

}
