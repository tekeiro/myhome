package org.keirobm.myhome.mediamanager.infrastructure.persistence.entities;

import java.util.List;

import org.keirobm.myhome.shared.BaseEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Table(name = "tv_shows")
public class TvShowEntity extends BaseEntity {

    @Column
    private String title;

    @Column
    private int year;

    @Column
    private String folderPath;

    @OneToMany(mappedBy = "tvShow", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TvShowSeasonEntity> seasons;

    public void addSeason(TvShowSeasonEntity season) {
        if (this.seasons == null) {
            this.seasons = new java.util.ArrayList<>();
        }
        this.seasons.add(season);
        season.setTvShow(this);
    }

    public void removeSeason(TvShowSeasonEntity season) {
        if (this.seasons != null) {
            this.seasons.remove(season);
            season.setTvShow(null);
        }
    }

}
