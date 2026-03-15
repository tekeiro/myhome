package org.keirobm.myhome.mediamanager.domain.watchlist.model;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class TvShow {
    private Long id;
    private String title;
    private int year;
    private String folderPath;

    private List<TvShowSeason> seasons;
}
