package br.com.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.local.converters.GenreListConverter
import br.com.local.converters.ListIntConverter
import br.com.local.converters.ListStringConverter
import br.com.local.dao.favorities.FavoritiesMovieRemoteKeysDao
import br.com.local.dao.favorities.FavoritiesMoviesDao
import br.com.local.dao.movie_details.MovieDetailsDao
import br.com.local.dao.trending.TrendingMovieRemoteKeysDao
import br.com.local.dao.trending.TrendingMoviesDao
import br.com.local.model.favorite.FavoritiesMovieEntity
import br.com.local.model.favorite.FavoritiesMoviesRemoteKeyEntity
import br.com.local.model.movie_details.MovieDetailsEntity
import br.com.local.model.trending.TrendingMovieEntity
import br.com.local.model.trending.TrendingMovieRemoteKeyEntity

@Database(
    entities = [
        TrendingMovieEntity::class,
        TrendingMovieRemoteKeyEntity::class,
        FavoritiesMovieEntity::class,
        FavoritiesMoviesRemoteKeyEntity::class,
        MovieDetailsEntity::class
    ],
    version = 1,
)
@TypeConverters(ListIntConverter::class, ListStringConverter::class, GenreListConverter::class)
abstract class MainDatabase : RoomDatabase() {
    abstract fun trendingMoviesDao(): TrendingMoviesDao
    abstract fun trendingMovieRemoteKeysDao(): TrendingMovieRemoteKeysDao

    abstract fun favoritiesMoviesDao() : FavoritiesMoviesDao

    abstract fun favoritiesMovieRemoteKeysDao() : FavoritiesMovieRemoteKeysDao

    abstract fun moviesDao () : MovieDetailsDao
}