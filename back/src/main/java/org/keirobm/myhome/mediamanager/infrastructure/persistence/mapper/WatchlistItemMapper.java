package org.keirobm.myhome.mediamanager.infrastructure.persistence.mapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.keirobm.myhome.mediamanager.domain.search.model.WatchlistItem;
import org.keirobm.myhome.mediamanager.domain.search.model.WatchlistItemNode;
import org.keirobm.myhome.mediamanager.infrastructure.persistence.entities.WatchlistItemEntity;
import org.keirobm.myhome.mediamanager.infrastructure.persistence.entities.WatchlistItemNodeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WatchlistItemMapper {

    @Mapping(target = "nodes", ignore = true) // We'll handle nodes separately in the adapter
    @Mapping(target = "episodesPerSeason", expression = "java(parseEpisodesPerSeason(entity.getEpisodesPerSeason()))")  
    WatchlistItem toDomain(WatchlistItemEntity entity);
    
    default List<Integer> parseEpisodesPerSeason(String episodesPerSeason) {
        if (episodesPerSeason == null || episodesPerSeason.isEmpty()) {
            return new ArrayList<>();
        }
        return Arrays.stream(episodesPerSeason.split(","))
                     .map(String::trim)
                     .map(Integer::parseInt)
                     .collect(Collectors.toList());
    }

    
    WatchlistItemNode nodeToDomain(WatchlistItemNodeEntity entity);
    
}
