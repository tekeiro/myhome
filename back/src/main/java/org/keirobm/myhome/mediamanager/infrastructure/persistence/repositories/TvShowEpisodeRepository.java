package org.keirobm.myhome.mediamanager.infrastructure.persistence.repositories;

import org.keirobm.myhome.mediamanager.infrastructure.persistence.entities.TvShowEpisodeEntity;
import org.keirobm.myhome.shared.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TvShowEpisodeRepository extends BaseRepository<TvShowEpisodeEntity, Long> {

}
