package org.keirobm.myhome.mediamanager.infrastructure.persistence.mapper;

import org.keirobm.myhome.mediamanager.domain.watchlist.model.Film;
import org.keirobm.myhome.mediamanager.infrastructure.persistence.entities.FilmEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FilmEntityMapper {

    Film toDomain(FilmEntity entity);

    FilmEntity toEntity(Film film);

}
