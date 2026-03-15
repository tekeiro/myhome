package org.keirobm.myhome.mediamanager.infrastructure.persistence.mapper;

import org.keirobm.myhome.mediamanager.domain.watchlist.model.TvShow;
import org.keirobm.myhome.mediamanager.infrastructure.persistence.entities.TvShowEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {TvShowSeasonEntityMapper.class})
public interface TvShowEntityMapper {

    TvShow toDomain(TvShowEntity entity);

}
