package uk.ac.tees.mad.w9617422.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import uk.ac.tees.mad.w9617422.data.remote.ApiService
import uk.ac.tees.mad.w9617422.data.remote.response.CastResponse
import uk.ac.tees.mad.w9617422.data.remote.response.WatchProviderResponse
import uk.ac.tees.mad.w9617422.model.Film
import uk.ac.tees.mad.w9617422.paging.*
import uk.ac.tees.mad.w9617422.util.FilmType
import uk.ac.tees.mad.w9617422.util.Resource
import kotlinx.coroutines.flow.Flow
import timber.log.Timber
import javax.inject.Inject

class FilmRepository @Inject constructor(
    private val api: ApiService
) {
    fun getTrendingFilms(filmType: FilmType): Flow<PagingData<Film>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 20),
            pagingSourceFactory = {
                TrendingFilmSource(api = api, filmType)
            }
        ).flow
    }

    fun getPopularFilms(filmType: FilmType): Flow<PagingData<Film>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 20),
            pagingSourceFactory = {
                PopularFilmSource(api = api, filmType)
            }
        ).flow
    }

    fun getTopRatedFilm(filmType: FilmType): Flow<PagingData<Film>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 20),
            pagingSourceFactory = {
                TopRatedFilmSource(api = api, filmType)
            }
        ).flow
    }

    fun getNowPlayingFilms(filmType: FilmType): Flow<PagingData<Film>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 20),
            pagingSourceFactory = {
                NowPlayingFilmSource(api = api, filmType)
            }
        ).flow
    }

    fun getUpcomingTvShows(): Flow<PagingData<Film>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 20),
            pagingSourceFactory = {
                UpcomingFilmSource(api = api)
            }
        ).flow
    }

    fun getBackInTheDaysFilms(filmType: FilmType): Flow<PagingData<Film>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 20),
            pagingSourceFactory = {
                BackInTheDaysFilmSource(api = api, filmType)
            }
        ).flow
    }

    fun getSimilarFilms(movieId: Int, filmType: FilmType): Flow<PagingData<Film>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 20),
            pagingSourceFactory = {
                SimilarFilmSource(api = api, filmId = movieId, filmType)
            }
        ).flow
    }

    fun getRecommendedFilms(movieId: Int, filmType: FilmType): Flow<PagingData<Film>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 20),
            pagingSourceFactory = {
                RecommendedFilmSource(api = api, filmId = movieId, filmType)
            }
        ).flow
    }

    /** Non-paging data */
    suspend fun getFilmCast(filmId: Int, filmType: FilmType): Resource<CastResponse> {
        val response = try {
            if (filmType == FilmType.MOVIE) api.getMovieCast(filmId = filmId)
            else api.getTvShowCast(filmId = filmId)
        } catch (e: Exception) {
            return Resource.Error("Error when loading movie cast")
        }
        return Resource.Success(response)
    }

    suspend fun getWatchProviders(
        filmType: FilmType, filmId: Int
    ): Resource<WatchProviderResponse> {
        val response = try {
            if (filmType == FilmType.MOVIE) api.getWatchProviders(
                filmPath = "movie", filmId = filmId
            )
            else api.getWatchProviders(filmPath = "tv", filmId = filmId)
        } catch (e: Exception) {
            return Resource.Error("Error when loading providers")
        }
        Timber.d("WATCH", response)
        return Resource.Success(response)
    }
}
