package org.keirobm.myhome.mediamanager.infrastructure.persistence.entities;

import org.keirobm.myhome.mediamanager.domain.queue.model.MediaCategory;
import org.keirobm.myhome.mediamanager.domain.search.model.SearchResultType;
import org.keirobm.myhome.shared.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "download_queue_items")
public class DownloadQueueItemEntity extends BaseEntity {

    @Column
    @Enumerated(EnumType.STRING)
    private MediaCategory contentType;
    
    @Column
    @Enumerated(EnumType.STRING)
    private SearchResultType searchResultType;


    @Column(nullable = false)
    private String hash;

    @Column
    private String filename;

    @Column
    private double percentage;

    @Column(nullable = false)
    private Long filmOrTvShowId;

    @Column(nullable = true)
    private Integer season;

    @Column(nullable = true)
    private Integer episode;

}
