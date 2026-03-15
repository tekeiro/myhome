package org.keirobm.myhome.mediamanager.infrastructure.persistence.repositories;

import org.keirobm.myhome.mediamanager.infrastructure.persistence.entities.WatchlistItemEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WatchlistItemRepository extends CrudRepository<WatchlistItemEntity, Long>, PagingAndSortingRepository<WatchlistItemEntity, Long>    {

}
