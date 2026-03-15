package org.keirobm.myhome.mediamanager.domain.search.model;

import java.util.List;
import java.util.ArrayList;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class WatchlistItem {
    private Long id;
    private String title;
    private MediaCategory category;

    private int year;
    
    // --- For tv shows
    private int seasons;
    private List<Integer> episodesPerSeason;
    // --- For tv shows

    // --- downloading status
    private String folderPath;
    // --- downloading status
    
    @Builder.Default
    private List<WatchlistItemNode> nodes = new ArrayList<>();


    public void createNodes() {
        if (category == MediaCategory.FILM) {
            this.nodes = List.of(WatchlistItemNode.builder()
                .title(title)
                .watched(false)
                .build());
        } else if (category == MediaCategory.TV_SHOW) {
            this.nodes = this.createTvShowNodes();
        }
    }

    private List<WatchlistItemNode> createTvShowNodes() {
        List<WatchlistItemNode> nodes = new java.util.ArrayList<>();
        for (int season = 1; season <= seasons; season++) {
            int episodes = episodesPerSeason.get(season - 1);
            for (int episode = 1; episode <= episodes; episode++) {
                nodes.add(WatchlistItemNode.builder()
                    .title(String.format("%s S%02dE%02d", title, season, episode))
                    .season(season)
                    .episode(episode)
                    .watched(false)
                    .build());
            }
        }
        return nodes;
    }


}
