package com.reisdeveloper.domain

import com.reisdeveloper.data.dataModel.BelongsToCollection
import com.reisdeveloper.data.dataModel.Favorite
import com.reisdeveloper.data.dataModel.Genre
import com.reisdeveloper.data.dataModel.Movie
import com.reisdeveloper.data.dataModel.MovieDetails
import com.reisdeveloper.data.dataModel.MovieList
import com.reisdeveloper.data.dataModel.MovieVideo
import com.reisdeveloper.data.dataModel.MovieVideos
import com.reisdeveloper.data.dataModel.ProductionCompany
import com.reisdeveloper.data.dataModel.ProductionCountry
import com.reisdeveloper.data.dataModel.SpokenLanguage

fun getMockMovieList() = MovieList(
    page = 1,
    results = listOf(getMockMovie()),
    totalPages = 1,
    totalResults = 1
)

fun getMockMovie() = Movie(
    adult = true,
    backdropPath = "backdropPath test",
    genreIds = listOf(1),
    id = 1,
    originalLanguage = "originalLanguage test",
    originalTitle = "originalTitle test",
    overview = "overview test",
    popularity = 1.0,
    posterPath = "posterPath test",
    releaseDate = "releaseDate test",
    title = "title test",
    video = true,
    voteAverage = 1.0,
    voteCount = 1
)


fun getMockMovieDetails() = MovieDetails(
    adult = true,
    backdropPath = "backdropPath test",
    belongsToCollection = getMockBelongsToCollection(),
    budget = 1,
    genres = listOf(getMockGenre()),
    homepage = "homepage test",
    id = 1,
    imdbId = "imdbId test",
    originalLanguage = "originalLanguage test",
    originalTitle = "originalTitle test",
    overview = "overview test",
    popularity = 1.0,
    posterPath = "posterPath test",
    productionCompanies = listOf(getMockProductionCompany()),
    productionCountries = listOf(getMockProductionCountry()),
    releaseDate = "releaseDate test",
    revenue = 1,
    runtime = 1,
    spokenLanguages = listOf(getMockSpokenLanguage()),
    status = "status test",
    tagline = "tagline test",
    title = "title test",
    video = true,
    voteAverage = 1.0,
    voteCount = 1
)

fun getMockSpokenLanguage() = SpokenLanguage(
    englishName = "englishName test",
    iso6391 = "iso6391 test",
    name = "name test"
)

fun getMockProductionCountry() = ProductionCountry(
    iso31661 = "iso31661 test",
    name = "name test"
)

fun getMockProductionCompany() = ProductionCompany(
    id = 1,
    logoPath = "logoPath test",
    name = "name test",
    originCountry = "originCountry test"
)

fun getMockGenre() = Genre(
    id = 1,
    name = "name test"
)

fun getMockBelongsToCollection() = BelongsToCollection(
    backdropPath = "backdropPath test",
    id = 1,
    name = "name test",
    posterPath = "posterPath test"
)

fun getMockMovieVideos() = MovieVideos(
    id = 1,
    results = listOf(getMockMovieVideo())
)

fun getMockMovieVideo() = MovieVideo(
    id = "id test",
    iso31661 = "iso31661 test",
    iso6391 = "iso6391 test",
    key = "key test",
    name = "name test",
    official = true,
    publishedAt = "publishedAt test",
    site = "site test",
    size = 1,
    type = "type test"
)

fun getMockFavorite() = Favorite(
    favorite = true,
    mediaId = 1,
    mediaType = "mediaType test"
)