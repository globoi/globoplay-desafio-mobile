package com.maslima.globo_play_recrutamento.database

import com.maslima.globo_play_recrutamento.domain.model.Movie
import com.maslima.globo_play_recrutamento.domain.util.DomainMapper

class MovieDatabaseMapper : DomainMapper<MovieDatabaseItem, Movie> {
    override fun mapToDomainModel(model: MovieDatabaseItem) = Movie(
        adult = model.adult ?: false,
        backdropPath = model.backdrop_path ?: "",
        id = model.id ?: -1,
        originalLanguage = model.original_language ?: "",
        originalTitle = model.original_title ?: "",
        overview = model.overview ?: "",
        popularity = model.popularity ?: 0.0,
        posterPath = model.poster_path ?: "",
        releaseDate = model.release_date ?: "",
        title = model.title ?: "",
        video = model.video ?: false,
        voteAverage = model.vote_average ?: 0.0,
        voteCount = model.vote_count ?: -1
    )

    override fun mapFromDomainModel(domainModel: Movie) = MovieDatabaseItem(
        adult = domainModel.adult,
        backdrop_path = domainModel.backdropPath,
        id = domainModel.id,
        original_language = domainModel.originalLanguage,
        original_title = domainModel.originalTitle,
        overview = domainModel.overview,
        popularity = domainModel.popularity,
        poster_path = domainModel.posterPath,
        release_date = domainModel.releaseDate,
        title = domainModel.title,
        video = domainModel.video,
        vote_average = domainModel.voteAverage,
        vote_count = domainModel.voteCount
    )

    fun toDomainList(initial: List<MovieDatabaseItem>): List<Movie> {
        return initial.map {
            mapToDomainModel(it)
        }
    }

    fun fromDomainList(initial: List<Movie>): List<MovieDatabaseItem> {
        return initial.map {
            mapFromDomainModel(it)
        }
    }
}