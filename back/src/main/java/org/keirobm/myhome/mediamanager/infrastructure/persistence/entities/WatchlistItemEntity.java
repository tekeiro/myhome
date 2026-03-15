package org.keirobm.myhome.mediamanager.infrastructure.persistence.entities;

import org.keirobm.myhome.mediamanager.domain.search.model.MediaCategory;
import org.keirobm.myhome.shared.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "watchlist_items")
public class WatchlistItemEntity extends BaseEntity {

    @Column
    private String title;

    @Enumerated(jakarta.persistence.EnumType.STRING)
    @Column
    private MediaCategory category;

    @Column
    private int year;

    // --- For tv shows
    @Column
    private int seasons;
    @Column(length = 1000)
    private String episodesPerSeason; // Stored as a comma-separated string, e.g. "10, 8, 12"
    // --- For tv shows

    // --- downloading status
    @Column(length = 1000)
    private String folderPath;
    // --- downloading status

}
