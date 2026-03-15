package org.keirobm.myhome.mediamanager.infrastructure.persistence.adapter;

import org.keirobm.myhome.mediamanager.application.search.NewSearchUseCase;
import org.keirobm.myhome.mediamanager.domain.watchlist.model.Film;
import org.keirobm.myhome.mediamanager.domain.watchlist.model.TvShow;
import org.keirobm.myhome.mediamanager.domain.watchlist.port.MediaLibraryPort;
import org.keirobm.myhome.mediamanager.infrastructure.persistence.entities.FilmEntity;
import org.keirobm.myhome.mediamanager.infrastructure.persistence.entities.TvShowEntity;
import org.keirobm.myhome.mediamanager.infrastructure.persistence.entities.TvShowEpisodeEntity;
import org.keirobm.myhome.mediamanager.infrastructure.persistence.entities.TvShowSeasonEntity;
import org.keirobm.myhome.mediamanager.infrastructure.persistence.mapper.FilmEntityMapper;
import org.keirobm.myhome.mediamanager.infrastructure.persistence.mapper.TvShowEntityMapper;
import org.keirobm.myhome.mediamanager.infrastructure.persistence.repositories.FilmRepository;
import org.keirobm.myhome.mediamanager.infrastructure.persistence.repositories.TvShowEpisodeRepository;
import org.keirobm.myhome.mediamanager.infrastructure.persistence.repositories.TvShowRepository;
import org.keirobm.myhome.mediamanager.infrastructure.persistence.repositories.TvShowSeasonRepository;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class MediaLibraryAdapter implements MediaLibraryPort {

    private final FilmRepository filmRepository;

    private final FilmEntityMapper filmEntityMapper;

    private final TvShowRepository tvShowRepository;

    private final TvShowSeasonRepository tvShowSeasonRepository;

    private final TvShowEpisodeRepository tvShowEpisodeRepository;

    private final TvShowEntityMapper tvShowEntityMapper;


    @Override
    public Film searchOrCreateFilm(String title, int year) {
        return this.filmRepository.findByTitleAndYear(title, year)
            .map(this.filmEntityMapper::toDomain)
            .orElseGet(() -> {
                final FilmEntity newFilmEntity = new FilmEntity();
                newFilmEntity.setTitle(title);
                newFilmEntity.setYear(year);
                newFilmEntity.setFolderPath("");
                newFilmEntity.setWatched(false);
                return this.filmEntityMapper.toDomain(this.filmRepository.save(newFilmEntity));
            });
    }

    @Override
    public TvShow searchOrCreateTvShow(String title, int year, int season, int episode) {
        final var tvShowEntity = this.tvShowRepository.findByTitleAndYear(title, year);
        tvShowEntity.ifPresent(tvShow -> this.checkIfNeedsToCreateSeasonOrEpisode(tvShow, season, episode));
        return tvShowEntity
            .map(this.tvShowEntityMapper::toDomain)
            .orElseGet(() -> {
                final var newTvShowEntity = new TvShowEntity();
                newTvShowEntity.setTitle(title);
                newTvShowEntity.setYear(year);
                newTvShowEntity.setFolderPath("");
                this.tvShowRepository.save(newTvShowEntity);
                this.checkIfNeedsToCreateSeasonOrEpisode(newTvShowEntity, season, episode);
                return this.tvShowEntityMapper.toDomain(newTvShowEntity);
            });
    }

    private void checkIfNeedsToCreateSeasonOrEpisode(TvShowEntity tvShow, int season, int episode) {
        final var seasonEntity = tvShow.getSeasons().stream().filter(s -> s.getNumber() == season).findFirst()
            .orElseGet(() -> {
                final var newSeason = new TvShowSeasonEntity();
                newSeason.setNumber(season);
                newSeason.setTvShow(tvShow);
                this.tvShowSeasonRepository.save(newSeason);
                tvShow.addSeason(newSeason);
                this.tvShowRepository.save(tvShow);
                return newSeason;
            });
        seasonEntity.getEpisodes().stream().filter(e -> e.getNumber() == episode).findFirst()
            .orElseGet(() -> {
                final var newEpisode = new TvShowEpisodeEntity();
                newEpisode.setNumber(episode);
                newEpisode.setSeason(seasonEntity);
                this.tvShowEpisodeRepository.save(newEpisode);
                seasonEntity.addEpisode(newEpisode);
                this.tvShowSeasonRepository.save(seasonEntity);
                return newEpisode;
            });
    }

}
