package org.keirobm.myhome.mediamanager.domain.watchlist.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class TvShowEpisode {
    private int number;
}
