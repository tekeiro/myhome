package org.keirobm.myhome.mediamanager.infrastructure.persistence.mapper;

import org.keirobm.myhome.mediamanager.domain.watchlist.model.TvShowSeason;
import org.keirobm.myhome.mediamanager.infrastructure.persistence.entities.TvShowSeasonEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {TvShowEpisodeEntityMapper.class})
public interface TvShowSeasonEntityMapper {

    TvShowSeason toDomain(TvShowSeasonEntity entity);

}
