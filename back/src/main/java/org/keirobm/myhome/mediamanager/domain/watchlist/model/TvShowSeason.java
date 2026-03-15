package org.keirobm.myhome.mediamanager.domain.watchlist.model;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class TvShowSeason {
    private int number;

    private List<TvShowEpisode> episodes;
}
