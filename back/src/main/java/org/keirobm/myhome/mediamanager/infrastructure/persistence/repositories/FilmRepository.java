package org.keirobm.myhome.mediamanager.infrastructure.persistence.repositories;

import java.util.Optional;

import org.keirobm.myhome.mediamanager.infrastructure.persistence.entities.FilmEntity;
import org.keirobm.myhome.shared.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository extends BaseRepository<FilmEntity, Long> {
    Optional<FilmEntity> findByTitleAndYear(String title, int year);
}
