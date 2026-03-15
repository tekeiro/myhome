package org.keirobm.myhome.mediamanager.infrastructure.persistence.entities;

import org.keirobm.myhome.mediamanager.domain.search.model.DownloadStatus;
import org.keirobm.myhome.mediamanager.domain.search.model.WatchlistItem;
import org.keirobm.myhome.shared.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "watchlist_items_nodes")
public class WatchlistItemNodeEntity extends BaseEntity {

    @Column
    private String title;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private WatchlistItemEntity parent;

    // --- For tv shows
    @Column
    private int season;
    @Column
    private int episode;
    // --- For tv shows

    @Column
    private boolean watched;

    // --- downloading status
    @Column
    @Enumerated(jakarta.persistence.EnumType.STRING)
    private DownloadStatus downloadStatus;

    @Column
    private Double downloadProgress; // 0.0 to 100.0

    @Column(length = 1000)
    private String filePath;
    // --- downloading status

}
