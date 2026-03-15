package org.keirobm.myhome.mediamanager.infrastructure.persistence.entities;

import org.keirobm.myhome.shared.BaseAudit;
import org.keirobm.myhome.shared.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Table(name = "tv_show_episodes")
public class TvShowEpisodeEntity extends BaseAudit {

    @Id
    private String id;

    @Column
    private int number;

    @ManyToOne
    @JoinColumn(name = "season_id", nullable = false, foreignKey = @ForeignKey(name = "fk_season_id"))
    private TvShowSeasonEntity season;

    @PrePersist
    public void generateId() {
        if (this.id == null) {
            this.id = String.format("%s_S%02dE%02d", this.season.getTvShow().getId(), this.season.getNumber(), this.number);
        }
    }

}
