package org.keirobm.myhome.mediamanager.infrastructure.persistence.entities;

import java.util.List;

import org.keirobm.myhome.shared.BaseAudit;
import org.keirobm.myhome.shared.BaseEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "tv_show_seasons")
public class TvShowSeasonEntity extends BaseAudit {

    @Id
    private String id;

    @Column
    private int number;

    @ManyToOne
    @JoinColumn(name = "tv_show_id", nullable = false, foreignKey = @ForeignKey(name = "fk_tv_show_id"))
    private TvShowEntity tvShow;

    @OneToMany(mappedBy = "season", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TvShowEpisodeEntity> episodes;

    public void addEpisode(TvShowEpisodeEntity episode) {
        if (this.episodes == null) {
            this.episodes = new java.util.ArrayList<>();
        }
        this.episodes.add(episode);
        episode.setSeason(this);
    }

    public void removeEpisode(TvShowEpisodeEntity episode) {
        if (this.episodes != null) {
            this.episodes.remove(episode);
            episode.setSeason(null);
        }
    }

    @PrePersist
    public void generateId() {
        if (this.id == null) {
            this.id = String.format("%s_S%02d", this.tvShow.getId(), this.number);
        }
    }


}
