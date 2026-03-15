package org.keirobm.myhome.mediamanager.domain.search.model;

import org.keirobm.myhome.mediamanager.domain.queue.model.MediaCategory;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SearchResultType {
    FILM(MediaCategory.FILM),
    TV_SHOW(MediaCategory.TV_SHOW),
    TV_SHOW_EPISODE(MediaCategory.TV_SHOW),
    TV_SHOW_SEASON(MediaCategory.TV_SHOW),
    ;

    private final MediaCategory contentType;
}
