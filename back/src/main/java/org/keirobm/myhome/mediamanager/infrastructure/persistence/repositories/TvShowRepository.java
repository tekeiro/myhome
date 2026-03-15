package org.keirobm.myhome.mediamanager.infrastructure.persistence.repositories;

import java.util.Optional;

import org.keirobm.myhome.mediamanager.infrastructure.persistence.entities.TvShowEntity;
import org.keirobm.myhome.shared.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TvShowRepository extends BaseRepository<TvShowEntity, Long> {
    Optional<TvShowEntity> findByTitleAndYear(String title, int year);
}
