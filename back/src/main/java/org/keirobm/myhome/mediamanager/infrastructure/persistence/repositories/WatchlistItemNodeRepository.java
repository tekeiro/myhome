package org.keirobm.myhome.mediamanager.infrastructure.persistence.repositories;

import java.util.List;

import org.keirobm.myhome.mediamanager.infrastructure.persistence.entities.WatchlistItemNodeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WatchlistItemNodeRepository extends CrudRepository<WatchlistItemNodeEntity, Long>, PagingAndSortingRepository<WatchlistItemNodeEntity, Long> {
    List<WatchlistItemNodeEntity> findByParentId(Long parentId);
}
