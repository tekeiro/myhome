package org.keirobm.myhome.mediamanager.infrastructure.persistence.mapper;

import org.keirobm.myhome.mediamanager.domain.watchlist.model.TvShowEpisode;
import org.keirobm.myhome.mediamanager.infrastructure.persistence.entities.TvShowEpisodeEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TvShowEpisodeEntityMapper {

    TvShowEpisode toDomain(TvShowEpisodeEntity entity);

}
