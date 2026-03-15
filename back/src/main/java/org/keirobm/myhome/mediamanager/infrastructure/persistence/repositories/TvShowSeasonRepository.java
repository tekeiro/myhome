package org.keirobm.myhome.mediamanager.infrastructure.persistence.repositories;

import org.keirobm.myhome.mediamanager.infrastructure.persistence.entities.TvShowSeasonEntity;
import org.keirobm.myhome.shared.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TvShowSeasonRepository extends BaseRepository<TvShowSeasonEntity, Long> {

}
